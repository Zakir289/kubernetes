/**
 * Created by zakir on 14/2/16.
 */

import io.fabric8.kubernetes.api.model.KubernetesList;
import io.fabric8.kubernetes.api.model.KubernetesListBuilder;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.api.model.PodListBuilder;
import io.fabric8.kubernetes.api.model.ReplicationController;
import io.fabric8.kubernetes.api.model.ReplicationControllerBuilder;
import io.fabric8.kubernetes.api.model.SecurityContextConstraintsList;
import io.fabric8.kubernetes.api.model.SecurityContextConstraintsListBuilder;
import io.fabric8.kubernetes.api.model.extensions.JobBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.Watch;
import io.fabric8.kubernetes.client.dsl.ExecWatch;

import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;



public class KubernetesAccessTest {

    //list
    @Test
    public void listPods() {
        KubernetesAccess test = new KubernetesAccess();
        test.getPods();
    }

    @Test
    public void listServices(){
        KubernetesAccess test = new KubernetesAccess();
        test.getServices();
    }

    @Test
    public void listNameSpaces(){
        KubernetesAccess test = new KubernetesAccess();
        test.getNamespaces();
    }

    @Test
    public void listReplicationContainers(){
        KubernetesAccess test = new KubernetesAccess();
        test.getReplicationContainers();
    }


    //create

    @Test
    public void createNamespace(){
        String nameSpace = "testnamespace";
        KubernetesAccess test = new KubernetesAccess();
        test.createNamespace(nameSpace);
    }

    @Test
    public void createService(){
       String nameSpace = "default", serviceName="frontend";
        int port = 80;
        KubernetesAccess test = new KubernetesAccess();
        test.createService(nameSpace,serviceName,port);
   }


    @Test
    public void createPod(){
        KubernetesAccess test = new KubernetesAccess();
        test.createPod();
    }

    //delete

    @Test
    public void deleteNamespace(){
        String nameSpace = "testnamespace";
        KubernetesAccess test = new KubernetesAccess();
        test.deleteNamespace(nameSpace);
    }

    @Test
    public void deletePod(){
        String nameSpace="default", podName="redis-master";
        KubernetesAccess test = new KubernetesAccess();
        test.deleteNamespace(nameSpace);
    }

    @Test
    public void deleteReplicationContainer(){
        String nameSpace="default", rcName="";
        KubernetesAccess test = new KubernetesAccess();
        test.deleteRC(nameSpace,rcName);
    }

    @Test
    public void deleteService(){
        String nameSpace ="default", serviceName="frontend";
        KubernetesAccess test = new KubernetesAccess();
        test.deleteService(nameSpace,serviceName);
    }
}
