package org.witchcraft.users.idreslover;


public class IdentityAddressResolver extends org.jbpm.identity.mail.IdentityAddressResolver{

	/**
	 * 
	 */
	private static final long serialVersionUID = -947350246139489225L;
	
	

	@Override
	public Object resolveAddress(String actorId) {
		// TODO Auto-generated method stub
		System.out.println("resolving id for " + actorId);
		return "singhjess@gmail.com";
	}

}
