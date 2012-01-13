/*
 ******************************************************************
File: org.mkcl.els.service.impl.DistrictServiceImpl.java
Copyright (c) 2011, sandeeps, ${company}
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
package org.mkcl.els.service.impl;

import org.mkcl.els.domain.District;

import org.mkcl.els.service.IDistrictService;
import org.springframework.stereotype.Service;

/**
 * The Class DistrictServiceImpl.
 *
 * @author sandeeps
 * @version v1.0.0
 */
@Service
public class DistrictServiceImpl extends GenericServiceImpl<District, Long>
        implements IDistrictService {

    /** The District repository. */
    /*
     * private DistrictRepository districtRepository;
     *//**
     * Sets the district repository.
     *
     * @param DistrictRepository the new district repository
     */
    /*
     * @Autowired public void setDistrictRepository(DistrictRepository
     * DistrictRepository) { this.dao = DistrictRepository;
     * this.districtRepository = DistrictRepository; }
     *
     * (non-Javadoc)
     *
     * @see org.mkcl.els.service.IDistrictService#findByName(java.lang.String)
     *
     * @Override public District findByName(String name) { return
     * districtRepository.findByName(name); }
     *
     * @Override public List<District> findDistrictsByStateId(Long stateId) {
     * return districtRepository.findDistrictsByStateId(stateId); }
     *
     * @Override public List<District> findDistrictsByStateId(Long id, String
     * property, boolean descOrder) { return
     * districtRepository.findDistrictsByStateId(id, property, descOrder); }
     *
     * @Override public List<District> findDistrictsByStateName(String
     * stateName) { // TODO Auto-generated method stub return
     * districtRepository.findDistrictsByStateName(stateName); }
     *
     * @Override public Set<District> findDistrictsByConstituencyId(Long
     * constituencyId) { return
     * districtRepository.findDistrictsByConstituencyId(constituencyId); }
     */

}
