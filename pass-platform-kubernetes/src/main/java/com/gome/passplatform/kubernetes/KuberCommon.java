/**
 * 
 */
package com.gome.passplatform.kubernetes;

/**
 * @author bailu-ds
 *
 */
public class KuberCommon {

	public static final String registry = "registry.hub.gome.com.cn/paas/";
	public static final String version = "v1";
	public static final String rc_prefix = "rc-name-";
	public static final String rc_label_prefix = "rc-label-";
	public static final String template_prefix = "podtemplatespec-name-";
	public static final String container_prefix = "container-name-";
	public static final String namespace_lable_prefix = "namespace-name-";
	public static final String service_prefix = "service-name-";
	public static final String service_lable_prefix = "service-label-";
	public static final String selector_prefix = "rc-label-";
	public static final String pod_prefix = "pod-name-";
	public static final String serviceaccount_prefix = "sa-name-";
	public static final String memory_unit = "Mi";
	public static final String rc_kind = "ReplicationController";
	public static final String volume_mount_prefix = "volume-mount-";
	public static final String volume_prefix = "volume";
	
	
	public static final String KUBE_EXCEP_NO_EXISTS = "404";
	
}
