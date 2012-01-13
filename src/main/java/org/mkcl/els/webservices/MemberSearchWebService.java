package org.mkcl.els.webservices;

import org.mkcl.els.common.vo.MemberSearchPage;
import org.mkcl.els.domain.MemberDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ws/membersearch")
public class MemberSearchWebService {

	@RequestMapping(value="/{criteria1}/{locale}")
	public @ResponseBody MemberSearchPage searchSingle(@PathVariable final String criteria1,@PathVariable final String locale){
		return MemberDetails.searchMemberDetails(criteria1, locale);
	}

	@RequestMapping(value="/{criteria1}/{page}/{rows}/{locale}")
	public @ResponseBody MemberSearchPage searchSinglePagination(@PathVariable final String criteria1,@PathVariable final Integer page,@PathVariable final Integer rows,@PathVariable final String locale){
		return MemberDetails.searchMemberDetails(criteria1, page, rows, locale);
	}

	@RequestMapping(value="/{criteria1}/{criteria2}/{locale}")
	public @ResponseBody MemberSearchPage searchDouble(@PathVariable final String criteria1,@PathVariable final String criteria2,@PathVariable final String locale){
		return MemberDetails.searchMemberDetails(criteria1, criteria2, locale);
	}

	@RequestMapping(value="/{criteria1}/{criteria2}/{page}/{rows}/{locale}")
	public @ResponseBody MemberSearchPage searchDoublePagination(@PathVariable final String criteria1,@PathVariable final String criteria2,@PathVariable final Integer page,@PathVariable final Integer rows,@PathVariable final String locale){
		return MemberDetails.searchMemberDetails(criteria1, criteria2, page, rows, locale);
	}



}
