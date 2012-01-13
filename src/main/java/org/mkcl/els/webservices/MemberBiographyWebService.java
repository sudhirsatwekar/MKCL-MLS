package org.mkcl.els.webservices;

import javax.servlet.http.HttpServletResponse;

import org.mkcl.els.common.vo.MemberBiographyVO;
import org.mkcl.els.domain.Document;
import org.mkcl.els.domain.MemberDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ws/biography")
public class MemberBiographyWebService {

	@RequestMapping(value="/{id}/{locale}")
	public @ResponseBody MemberBiographyVO getBiography(@PathVariable("id") final long id,@PathVariable("locale") final String locale){
		return MemberDetails.findBiography(id,locale);
	}

	@RequestMapping(value="/photo/{tag}")
	public @ResponseBody byte[] getPhoto(@PathVariable("tag") final String tag,final HttpServletResponse response){
		Document document=Document.findByTag(tag);
		return document.getFileData();
	}
}
