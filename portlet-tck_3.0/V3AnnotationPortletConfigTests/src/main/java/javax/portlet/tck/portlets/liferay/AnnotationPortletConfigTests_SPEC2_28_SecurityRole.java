package javax.portlet.tck.portlets.liferay;

import javax.portlet.Portlet;
import javax.portlet.annotations.PortletConfiguration;
import javax.portlet.annotations.SecurityRoleRef;
import javax.portlet.tck.portlets.AnnotationPortletConfigTests_SPEC2_28_SecurityRoleBase;

@PortletConfiguration(
	portletName = "AnnotationPortletConfigTests_SPEC2_28_SecurityRole",
	roleRefs = @SecurityRoleRef(roleName = "User", roleLink = "User")
)
public class AnnotationPortletConfigTests_SPEC2_28_SecurityRole extends
	AnnotationPortletConfigTests_SPEC2_28_SecurityRoleBase implements
	Portlet {

	@Override
	public String getRoleName() {
		return "User";
	}
}
