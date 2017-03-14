package jp.or.adash.honniwa.controllers;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jp.or.adash.honniwa.dto.OwnerComment;
import jp.or.adash.honniwa.services.OwnerInfoService;



@Path("/web/admin/ownerinfo")
@Produces(MediaType.TEXT_HTML)
public class OwnerInfoController {
	
	@GET
	public Response show(@QueryParam("ownerId") String ownerId ,
			              @QueryParam("name") String name ,
			              @QueryParam("twitter") String twitter ,
			              @QueryParam("facebook") String facebook ,
			              @QueryParam("email") String email){
		
		List<OwnerComment> ownerList = new OwnerInfoService().getOwnerList(ownerId,name,twitter,facebook,email);
		
		return ResponseUtil.view("/ownerinfo.jsp", ownerList);
	}
	
	@POST
	public Response insert(@FormParam("ownerId") String ownerId ,
							  @FormParam("name") String name ,
							  @FormParam("twitter") String twitter ,
							  @FormParam("facebook") String facebook ,
							  @FormParam("email") String email){

		OwnerComment ownerInfo = new OwnerInfoService().getOwnerInfo(ownerId,name,twitter,facebook,email);	
			
		return ResponseUtil.view("/ownercomment.jsp", ownerInfo);
	}
	
}
