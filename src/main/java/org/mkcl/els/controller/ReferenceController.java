/**
******************************************************************
File: org.mkcl.els.controller.ReferenceController.java
Copyright (c) 2012, sandeeps, MKCL
All rights reserved.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.

******************************************************************
 */

package org.mkcl.els.controller;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.mkcl.els.domain.Constituency;
import org.mkcl.els.domain.CustomParameter;
import org.mkcl.els.domain.District;
import org.mkcl.els.domain.Reference;
import org.mkcl.els.domain.Tehsil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The Class ReferenceController.
 *
 * @author sandeeps
 * @version v1.0.0
 */
@Controller
@RequestMapping("/ref")
public class ReferenceController extends BaseController {

   
    /**
     * Gets the districts by state id.
     *
     * @param stateId the state id
     * @param map the map
     * @return the districts by state id
     */
    @RequestMapping(value = "/{state_id}/districts", method = RequestMethod.GET)
    public @ResponseBody
    List<District> getDistrictsByStateId(@PathVariable("state_id") final Long stateId,
                                         final ModelMap map) {
        return District.findDistrictsByStateId(stateId);
    }

   
    /**
     * Gets the constituencies by district id.
     *
     * @param districtId the district id
     * @param map the map
     * @return the constituencies by district id
     */
    @RequestMapping(value = "/{district_id}/constituencies",
            method = RequestMethod.GET)
    public @ResponseBody
    List<Constituency> getConstituenciesByDistrictId(@PathVariable("district_id")
                                                     final Long districtId,
                                                     final ModelMap map) {
        return Constituency.findConstituenciesByDistrictId(districtId);
    }

   
    /**
     * Gets the tehsils by district id.
     *
     * @param districtId the district id
     * @param map the map
     * @param request the request
     * @return the tehsils by district id
     */
    @RequestMapping(value = "/{district_id}/tehsils",
            method = RequestMethod.GET)
    public @ResponseBody
    List<Tehsil> getTehsilsByDistrictId(@PathVariable("district_id") final Long districtId,
                                        final ModelMap map,
                                        final HttpServletRequest request) {
        return Tehsil.findTehsilsByDistrictId(districtId);
    }

  
    /**
     * Gets the constituencies starting with.
     *
     * @param map the map
     * @param request the request
     * @return the constituencies starting with
     * Explaination for Internationalization
     * request.getParameter(param) doesn't seem to respect the request.setCharacterEncoding(encoding) as set
     * in the SetCharacterEncodingFilter in case of a GET request.Meaning the parameter is returned in the default encoding scheme
     * as specified in servlet 2.0 spec. i.e ISO 8859-1.This is acceptable in case of english language but is not acceptable
     * in case of multi languages support.
     * Reason:There exists no standard that defines how query string must be encoded before transmitting and
     * hence app-server developers left the encoding of the parameters in the default scheme suggested.But character encoding is set 
     * properly in case of POST requests as there exists standard(application/x-www-form-urlencoded) that defines the encoding of parameters 
     * transmitted as part of request body.
     * 
     * Solution:Obtain the parameters in the default encoding scheme(ISO-8859-1)
     * Then get the bytes from the string by using string.getBytes(encoding) method.Here string.getBytes() method
     * is avoided as it returns the bytes using java's default encoding of String i.e UTF16(Big Endian-MSB first)
     * resulting in data corruption.The encoding pass to getBytes(encoding) is ISO-8859-1
     *Then a new decoded string  in required format(utf-8)is constructed by using
     *String decodedString=new String(q.getBytes("ISO-8859-1"),"utf-8") which gives us the unicode string 
     *using utf-8 encoding.
     *Also before passing this string for further processing the trim() method must be called to remove
     *leading and trailing whitespaces.
     *For better customization default character encoding can be set as a custom parameter.
     *Similar changes should be made to all the methods.
     *Issue:Need to think of a better approach in case of exception(UnsupportedEncodingException).At present just returning
     *an empty collection. 
     */
    @RequestMapping(value = "/constituencies", method = RequestMethod.GET)
    public String getConstituenciesStartingWith(final ModelMap map,
                                                final HttpServletRequest request) {    	
    	String param=request.getParameter("term");
        String decodedString=null;
        List<Reference> constituencies=new ArrayList<Reference>();
        try {
        	decodedString=new String(param.getBytes("ISO-8859-1"),"UTF-8");
			constituencies = Constituency
            .findConstituenciesStartingWith(decodedString.trim());
			map.addAttribute("results", constituencies);
			return "constituencies";			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			//this is done so as to have a graceful degradation in case of exceptions
			map.addAttribute("results", constituencies);
			return "constituencies";
		}    	
    }
   
    /**
     * Gets the districts by constituency id.
     *
     * @param constituencyName the constituency name
     * @param map the map
     * @param request the request
     * @return the districts by constituency id
     * Issue:The method findByName in constituency should contain another parameter locale.
     * Alternate Explaination(Need Discussion):Since in the constituency controller there is a check
     * to prevent constituency with same names and it is locale based so the names will be unique 
     * in all locale and hence need for second parameter seems meaningless.But still need discussions.
     */
    @RequestMapping(value = "/data/{constituency_name}/districts",
            method = RequestMethod.GET)
    public @ResponseBody
    List<District> getDistrictsByConstituencyId(@PathVariable("constituency_name")
                                                final String constituencyName,
                                                final ModelMap map,
                                                final HttpServletRequest request) {
    	String decodedString = null;
    	List<District> districts=new ArrayList<District>();
		try {
			decodedString = new String(constituencyName.getBytes(CustomParameter.findByName("DEFAULT_URI_ENCODING").getValue()),CustomParameter.findByName("DEFAULT_ENCODING").getValue());
			 districts= District.findDistrictsByConstituencyId(
		                Constituency.findByName(decodedString.trim()).getId(), "name",
		                false);
			 return districts;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return districts;
		}        
        
    }
}
