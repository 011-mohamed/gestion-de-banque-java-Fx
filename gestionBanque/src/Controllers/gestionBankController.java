package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.text.TabExpander;

import entities.Client;
import entities.Compte;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class gestionBankController implements Initializable {

	@FXML
	TextField	numberCompte ;
	
	@FXML
	TextField numberClient ;
	
	@FXML
	Pane client ;
	
	@FXML
	Pane compte;
	
	@FXML
	Pane transactions ;
	
	@FXML
	Pane virement;
	
	
	@FXML
	TextField numCompte;
	
	@FXML
	TextField solde;
	
	@FXML
	TextField montantDecouvert;
	
	@FXML
	TextField montantDebit;
	
	@FXML
	TextField situation;

	@FXML
	ComboBox<Integer> titulaire;
	
	@FXML
	Label confirm;
	
	@FXML
	Label confirmCl;
	
	@FXML
	TextField cin;
	
	@FXML
	TextField nomClient ;
	
	@FXML
	TextField prenomClient;
	
	@FXML
	TextField mailClient;
	
	@FXML
	TableView<Client> tableClient;
	
	@FXML
	TableColumn<Client, Integer> cinTClient;
	
	@FXML
	TableColumn<Client, String> nomTClient;
	
	@FXML
	TableColumn<Client, String> prenomTClient;
	
	@FXML
	TableColumn<Client, String> mailTClient;
	
	ObservableList<Client> listClient ;
	
	@FXML
	TableView<Compte> tableCompte;
	
	@FXML
	TableColumn<Compte, Integer> numTC;
	
	@FXML
	TableColumn<Compte, Integer> soldeTC;
	
	@FXML
	TableColumn<Compte, Integer> montantTCDecouvert;
	
	
	@FXML
	TableColumn<Compte, Integer> montantTCDebit;
	
	@FXML
	TableColumn<Compte, String> etatTC;
	
	
	@FXML
	TableColumn<Compte, Integer> titulaireTC;
	
	@FXML
    private RadioButton debiter;
	
	@FXML
    private RadioButton retrait;
	
	 @FXML
	 private TextField tmont;
	 
	 @FXML
	 ComboBox<Integer> tncp;
	 
	 @FXML
	 private Label transConfirm;
	 
	 @FXML
	 TextField numEmetteur;
	 

	 @FXML
	 TextField numRecepteur;
	 
	 @FXML
	 TextField montViremment;
	 
	 @FXML
	 Label viremmentConfirm;
	
	
	 
	ObservableList<Compte> listCompte ;
	ObservableList<Compte> maxSoldeList ;
	ObservableList<Compte> compteDev ;
	
	
	
	int indexCp = -1 ;
	
	int indexCl = -1 ;
	
	service sc = new service();
	
	public void virement() {
		
		virement.setVisible(true);
		transactions.setVisible(false);
		compte.setVisible(false);
		client.setVisible(false);
	}
	
	
	
	public void transactions() {
		
		transactions.setVisible(true);
		compte.setVisible(false);
		client.setVisible(false);
		virement.setVisible(false);
		
		ArrayList<Integer> rs =  sc.recupererNumCp();
		ObservableList<Integer> listnumcp = FXCollections.observableArrayList(rs);
		tncp.setItems(listnumcp);
		
	}
	
	
	public void compte() {
		service sc = new service();
		
		sc.situationDecouvert();
		
		compte.setVisible(true);
		client.setVisible(false);
		transactions.setVisible(false);
		virement.setVisible(false);
		
		
		int ncpf = (sc.count("numCompte", "compte"))+1;
		numberCompte.setText(ncpf +" Compte");
		
		

		//pour table compte
		numTC.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("numCompte"));
		soldeTC.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("solde"));
		montantTCDecouvert.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("montantDecouverte"));
		montantTCDebit.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("montantDebit"));
		etatTC.setCellValueFactory(new PropertyValueFactory<Compte, String>("etatCompte"));
		titulaireTC.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("cin"));
		
		
		
		
		listCompte = sc.listercompte();
		tableCompte.setItems(listCompte);
		
		
		
	}
	public  void client() {
		service sc = new service();
		compte.setVisible(false);
		client.setVisible(true);
		virement.setVisible(false);
		transactions.setVisible(false);
		int nclf = (sc.count("cin","client"))+1;
		numberClient.setText(nclf +" Client");
		
		//pour le table de client
				cinTClient.setCellValueFactory(new PropertyValueFactory<>("cin"));
				nomTClient.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
				prenomTClient.setCellValueFactory(new PropertyValueFactory<>("prenomClient"));
				mailTClient.setCellValueFactory(new PropertyValueFactory<>("adresse"));
				
				listClient = sc.listerclient();
				tableClient.setItems(listClient);
				
		
		
		
	}
	
	public void ajouterCompte() {
		try {
			int num = Integer.parseInt(numCompte.getText());
			int s = Integer.parseInt(solde.getText());
			int dv = Integer.parseInt(montantDecouvert.getText());
			int db = Integer.parseInt(montantDebit.getText());
			String etat = situation.getText();
			int owner = Integer.parseInt(titulaire.getSelectionModel().getSelectedItem().toString());
			
			service sc = new service();
			
			if(!sc.ajouterCp( num, s, dv, db, etat, owner)) {
				
				Compte compte = new Compte(num, s, dv, db, etat, owner);
				listCompte.add(compte);
				tableCompte.setItems(listCompte);
				
				confirm.setText("votre compte a été ajouter !!");
				confirm.setVisible(true);
			}
			
		} catch (Exception e) {
			
		}
		
		
	}
	
	public void ajouterClient() {
		
		try {
			int cinC = Integer.parseInt(cin.getText());
			String nom = nomClient.getText();
			String prenom = prenomClient.getText();
			String email = mailClient.getText();
			
			service sc = new service();
			
			if(!sc.ajouterCl(cinC, nom, prenom, email)) {
				
				Client cl = new Client(cinC, nom, prenom, email);
				listClient.add(cl);
				tableClient.setItems(listClient);
				
				confirmCl.setText("le client a été ajouter !!");
				confirmCl.setVisible(true);
			}
			
		} catch (Exception e) {
			
		}
		
	}
	
	public void getSelectedCompte() {
		indexCp = tableCompte.getSelectionModel().getSelectedIndex();
		if (indexCp <=-1) {
			return ;
		}else {
		numCompte.setText(numTC.getCellData(indexCp).toString());
		solde.setVisible(false);
		montantDecouvert.setText(montantTCDecouvert.getCellData(indexCp).toString());
		montantDebit.setText(montantTCDebit.getCellData(indexCp).toString());
		situation.setVisible(false);
		titulaire.setVisible(false);
		}
	}
	
	public void getSelectedClient() {
		indexCl = tableClient.getSelectionModel().getSelectedIndex();
		if (indexCl <=-1 ) {
			return ;
		} else {
			cin.setText(cinTClient.getCellData(indexCl).toString());
			nomClient.setText(nomTClient.getCellData(indexCl).toString());
			prenomClient.setText(prenomTClient.getCellData(indexCl).toString());
			mailClient.setVisible(false);
		}
	}
	
	public void modifiercp() {
		
		
		try {
			int num = Integer.parseInt(numCompte.getText()); 
			int mntdv = Integer.parseInt(montantDecouvert.getText());
			int mntdb = Integer.parseInt(montantDebit.getText());
			
			
			if(sc.modifierCompte(num, mntdv, mntdb)) {
				listCompte.set(indexCp, new Compte(listCompte.get(indexCp).getNumCompte(),listCompte.get(indexCp).getSolde() , 
						mntdv, mntdb, listCompte.get(indexCp).getEtatCompte(), listCompte.get(indexCp).getCin()));
				
				confirm.setText("la modification a été enregistrée !");
				confirm.setVisible(true);
				numCompte.setVisible(true);
				solde.setVisible(true);
				montantDecouvert.setVisible(true);
				montantDebit.setVisible(true);
				situation.setVisible(true);
				titulaire.setVisible(true);
				clearCp();
			}
			
		} catch (Exception e) {
			
		}
		
	}
	
	public void modifiercl() {
		
		
		try {
			int c = Integer.parseInt(cin.getText());
			String n = nomClient.getText();
			String p = prenomClient.getText();  
			
			
			
			if(sc.modifierClient(c, n, p)) {
				listClient.set(indexCl, new Client(listClient.get(indexCl).getCin(), n, p, listClient.get(indexCl).getAdresse()));
				confirmCl.setText("votre donnée on été modifier !");
				confirmCl.setVisible(true);
				mailClient.setVisible(true);
				clearCl();
			}
			
		} catch (Exception e) {
			
		}
		
	}
	
	public void clearCp() {
		numCompte.clear();
		solde.clear();
		montantDebit.clear();
		montantDecouvert.clear();
		situation.clear();
		titulaire.getSelectionModel().select(-1);
	}
	
	public void clearCl() {
		cin.clear();
		nomClient.clear();
		prenomClient.clear();
		mailClient.clear();
	}
	
	public void supCompte() {
		indexCp = tableCompte.getSelectionModel().getSelectedIndex();
		 try {
			if(sc.supprimerCompte(numTC.getCellData(indexCp))) {
				confirm.setText("le compte a été suprrimer !");
				confirm.setVisible(true);
				numCompte.setVisible(true);
				solde.setVisible(true);
				montantDecouvert.setVisible(true);
				montantDebit.setVisible(true);
				situation.setVisible(true);
				titulaire.setVisible(true);
				numberCompte.setText(Integer.parseInt(numberCompte.getText())-1+ "compte");
				listCompte.remove(indexCp);
				indexCp = -1 ;
				
				clearCp();
			}
			 
			 
		} catch (Exception e) {
			
		}
	}
	 public void supClient() {
		 indexCl = tableClient.getSelectionModel().getSelectedIndex();
		 try {
			
			 if (sc.supprimerClient(cinTClient.getCellData(indexCl))) {
				confirmCl.setText("le client est supprimer !!");
				confirmCl.setVisible(true);
				mailClient.setVisible(true);
				numberClient.setText(Integer.parseInt(numberClient.getText())-1+"client");
				listClient.remove(indexCl);
				indexCl = -1 ;
				clearCl();
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	 }
	
	 public void Retrait() {
		 try {
			
			
			int n = Integer.parseInt(tncp.getSelectionModel().getSelectedItem().toString());
			int m = Integer.parseInt(tmont.getText().toString());
			 
			if (sc.transactionsRetrait(n, m)) {
				 transConfirm.setText("votre transactions a été effectuer !");
				 transConfirm.setVisible(true);
			}
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
	 }
	 public void Debiter() {
		 try {
				
				
				int n = Integer.parseInt(tncp.getSelectionModel().getSelectedItem().toString());
				int m = Integer.parseInt(tmont.getText().toString());
				 
				if (sc.transactionsDebiter(n, m)) {
					 transConfirm.setText("votre transactions a été effectuer !");
					 transConfirm.setVisible(true);
				}
				 
			} catch (Exception e) {
				// TODO: handle exception
			}
		 	
	 }
	 
	 
	 public void opperationV() {
		 try {
			int nE = Integer.parseInt(numEmetteur.getText());
			int nR = Integer.parseInt(numRecepteur.getText());
			int mnt = Integer.parseInt(montViremment.getText());
			
			if(sc.transactionsRetrait(nE, mnt) && sc.transactionsDebiter(nR, mnt)) {
				viremmentConfirm.setText("Operation reusite !!");
				viremmentConfirm.setVisible(true);
			}
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
	 }
	 
	 public void maxSolde () {
		 	
		 	numTC.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("numCompte"));
			soldeTC.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("solde"));
			montantTCDecouvert.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("montantDecouverte"));
			montantTCDebit.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("montantDebit"));
			etatTC.setCellValueFactory(new PropertyValueFactory<Compte, String>("etatCompte"));
			titulaireTC.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("cin"));
			
			maxSoldeList = sc.maxSolde();
			tableCompte.setItems(maxSoldeList);
			
			
	 }
	 
	 public void compteDecouvert() {
		 	numTC.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("numCompte"));
			soldeTC.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("solde"));
			montantTCDecouvert.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("montantDecouverte"));
			montantTCDebit.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("montantDebit"));
			etatTC.setCellValueFactory(new PropertyValueFactory<Compte, String>("etatCompte"));
			titulaireTC.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("cin"));
			
			compteDev = sc.compteDecouverte();
			tableCompte.setItems(compteDev);
	 }
	 public void logout(ActionEvent e) {
		 try {
			 Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/LoginMain.fxml"));
				Scene scene = new Scene(root);
				Stage stage =  (Stage) ((Node)e.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.setTitle("Gestion de banque");
				
				//pour que gestion de banque etrer aux milieu 
				
				Rectangle2D rectangle2d = Screen.getPrimary().getVisualBounds();
				stage.setX((rectangle2d.getWidth()- stage.getWidth())/2);
				stage.setX((rectangle2d.getHeight()- stage.getHeight())/2);
			
		} catch (Exception e2) {
			// TODO: handle exception
		}
		
	 }
	 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		//pour la statistique
		service sc = new service();
		int ncp = sc.count("numCompte", "compte");
		int ncl = sc.count("cin","client");
		//pour le comboBox de cin 
		numberCompte.setText(ncp +" Compte");
		numberClient.setText(ncl +" Client");
		
		ArrayList<Integer> res =  sc.recupererCin("cin", "client");
		ObservableList<Integer> listtitulaire = FXCollections.observableArrayList(res);
		titulaire.setItems(listtitulaire);
		
		
		
		
		
		
		
	}
	
	

}
