/**
 * Created by zakir on 14/2/16.
 */


import com.sun.org.apache.xml.internal.utils.NameSpace;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceBuilder;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

public class KubernetesAccess {

    private static final Logger logger = LoggerFactory.getLogger(KubernetesAccess.class);
    private static Config config;
    private static KubernetesClient client;

    public KubernetesAccess(){
        config = new ConfigBuilder().withMasterUrl("http://localhost:8080/").build();
        client = new DefaultKubernetesClient(config);
    }

    public static void main(String[] args) {
        String master ="http://localhost:8080/" ;
        if (args.length == 1) {
            master = args[0];
        }



        try {
//      log("Create namespace:", client.namespaces().create(new NamespaceBuilder().withNewMetadata().withName("thisisatest").endMetadata().build()));
            log("Deleted namespace:", client.namespaces().withName("thisisatest").delete());
//      log("Deleted testPod of Testing Namespace:", client.pods().inNamespace("thisisatest").withName("testpod").delete());
//      log("Deleted pod by label:", client.pods().withLabel("this", "works").delete());
        } catch (KubernetesClientException e) {
            logger.error(e.getMessage(), e);
        } finally {
//      client.namespaces().withName("thisisatest").delete();
            client.close();
        }
    }


    //regarding listing

    public static void getNamespaces(){
//        ArrayList nameSpacesList = (ArrayList) client.namespaces().list().getItems();
//        for(int i =0; i < nameSpacesList.size(); i++){
//            NameSpace nameSpace = (NameSpace) nameSpacesList.get(i);
//
//            System.out.println(nameSpace.getMetadata().name);
//        }

        System.out.println(
                client.namespaces().list().getItems()
        );

    }

    public static void getPods(){


        System.out.println(
                client.pods().list().getItems()
        );

    }

    public static void getServices(){
        System.out.println(
                client.services().list().getItems()
        );
    }

    public static void getReplicationContainers(){
        System.out.println(
                client.replicationControllers().list().getItems()
        );
    }


    //creating things

    public static void createNamespace(String nameSpace){
        log("Create namespace:", client.namespaces().create(new NamespaceBuilder().withNewMetadata().withName(nameSpace).endMetadata().build()));
    }

    public static void createService(String nameSpace, String serviceName, int port){

        log("Created service",
                client.services().inNamespace(nameSpace).createNew()
                        .withNewMetadata().withName(serviceName).endMetadata()
                        .withNewSpec()
                        .addNewPort().withPort(port).withNewTargetPort().withIntVal(80).endTargetPort().endPort()
                        .endSpec()
                        .done());
    }

    public static void createPod(){

        Pod pod1 = new PodBuilder().withNewMetadata().withName("pod1").endMetadata().build();



        client.pods().inNamespace("default").create(pod1);
    }


public static void deleteNamespace(String NameSpace){
    log("delete namespace:", client.namespaces().withName(NameSpace).delete());
}

    public static void deletePod(String nameSpace, String pod){
        log("Deleting the pod with specified namespace:", client.pods().inNamespace(nameSpace).withName(pod).delete());
    }

    public static void deleteRC(String nameSpace, String rcName){
        client.replicationControllers().inNamespace(nameSpace).withName(rcName).delete();
    }

    public static void deleteService(String nameSpace, String serviceName){
        client.services().inNamespace(nameSpace).withName(serviceName).delete();
    }


    private static void log(String action, Object obj) {
        logger.info("{}: {}", action, obj);
    }

    private static void log(String action) {
        logger.info(action);
    }

}

