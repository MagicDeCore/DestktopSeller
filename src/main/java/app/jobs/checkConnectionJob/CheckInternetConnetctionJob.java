package app.jobs.checkConnectionJob;

import app.services.ConnectionService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckInternetConnetctionJob implements Job {
    private ConnectionService connectionService = ConnectionService.getConnectionService();
//    private ClientService clientService = ClientService.getClientService();
//    private SyncService syncService = SyncService.getSyncService();


    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Starting Job");
        //todo PUSH products To Server according to the new logic
//        boolean isUnupdate = clientService.isUnupdatedClients();
//        ArrayList<ClientDom> unupdatedClients = clientService.getUnUploadedClients();
//        System.out.println("UNuploaded clients : " + unupdatedClients.size());
        //todo If new product were added to client shopping card then run sync
//        if (!unupdatedClients.isEmpty()) {
        if (true){
            try {
                if (connectionService.checkInternetConnection()) {
//                    syncService.updateUnuploadedClientsShoppingCards(unupdatedClients);
                }
            } catch (IOException ex) {
                Logger.getLogger(CheckInternetConnetctionJob.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(CheckInternetConnetctionJob.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Nothing to sync...");
        }
    }
}
