/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */
package com.gome.passplatform.kubernetes.interfaces;

import java.util.Map;

import com.gome.passplatform.kubernetes.exceptions.KubernetesClientException;
import com.gome.passplatform.kubernetes.modelv1.Namespace;
import com.gome.passplatform.kubernetes.modelv1.Pod;
import com.gome.passplatform.kubernetes.modelv1.PodList;
import com.gome.passplatform.kubernetes.modelv1.ReplicationController;
import com.gome.passplatform.kubernetes.modelv1.ReplicationControllerList;
import com.gome.passplatform.kubernetes.modelv1.Service;
import com.gome.passplatform.kubernetes.modelv1.ServiceAccount;
import com.gome.passplatform.kubernetes.modelv1.ServiceAccountList;
import com.gome.passplatform.kubernetes.modelv1.ServiceList;

public interface KubernetesAPIClientInterface {

    public static final String VERSION = "v1";
    public static final String CONTAINER_PORT = "8080";
    public static final String POD_PORT = "8080";
    public static final String NODE_PORT = "80";
    
    public boolean createNamespace(Namespace namespaces) throws KubernetesClientException;
    public boolean createSecret(String namespace) throws KubernetesClientException;
    

	/* Pod API */

	public Pod getPod(String namespaces, String podId) throws KubernetesClientException;
	public PodList getAllPods(String namespaces) throws KubernetesClientException;
	public PodList getRcPods(String namespaces, String rcName) throws KubernetesClientException;
	public PodList selectRcPods(String namespaces, String selectorName) throws KubernetesClientException;
	public Pod createPod(String namespaces, Pod pod) throws KubernetesClientException;
	public String deletePod(String namespaces, String podId) throws KubernetesClientException;
	
	
	/* Replication Controller API */
	
	public ReplicationController getReplicationController(String namespaces, String controllerId) throws KubernetesClientException;
	public ReplicationControllerList getAllReplicationControllers(String namespaces) throws KubernetesClientException;
	public ReplicationController createReplicationController(String namespaces, ReplicationController controller) throws KubernetesClientException;
    public ReplicationController updateReplicationController(String namespaces, String controllerId, int replicas, String imageName, String tag) throws KubernetesClientException;
	public String deleteReplicationController(String namespaces, String controllerId) throws KubernetesClientException;
	
	
	
	/* Services API */
	
	public Service getService(String namespaces, String serviceId) throws KubernetesClientException;
	public ServiceList getAllServices(String namespaces) throws KubernetesClientException;
	public Service createService(String namespaces, Service service) throws KubernetesClientException;
	public String deleteService(String namespaces, String serviceId) throws KubernetesClientException;
	
    public PodList getSelectedPods(String namespaces, Map<String, String> labels) throws KubernetesClientException;
    
    /* service account */
    
    public ServiceAccount getServiceAccount(String namespaces, String accountId) throws KubernetesClientException;
	public ServiceAccountList getAllServiceAccounts(String namespaces) throws KubernetesClientException;
	public ServiceAccount createServiceAccount(String namespaces, ServiceAccount account) throws KubernetesClientException;
	public String deleteServiceAccount(String namespaces, String accountId) throws KubernetesClientException;
    
    
}
