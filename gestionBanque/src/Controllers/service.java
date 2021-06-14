package Controllers;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;

import entities.Client;
import entities.Compte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;



public class service {

	PreparedStatement pst = null ;
	Connection con = null ;
	ResultSet res = null ;
	
	public service() {
		con = DB.getConnect();
	}
	
	public  int count(String col , String table) {
		try {
			pst= con.prepareStatement("select count("+col+") from "+table);
			res =pst.executeQuery();
			if (res.next()) {
				return Integer.parseInt(res.getString(1));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public  ArrayList<Integer> recupererCin(String col , String table) {
		try {
			pst= con.prepareStatement("select "+col+" from "+table);
			res =pst.executeQuery();
			
			final ArrayList<Integer> list = new ArrayList<>();
			while(res.next()){
				list.add(res.getInt("cin"));
			}	
				return list;
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<Integer> listnull = null;
		return listnull;
		
	}
	
	public ArrayList<Integer> recupererNumCp(){
		try {
			pst= con.prepareStatement("select numCompte from compte");
			res =pst.executeQuery();
			
			final ArrayList<Integer> list = new ArrayList<>();
			while(res.next()){
				list.add(res.getInt("numCompte"));
			}	
				return list;
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		ArrayList<Integer> listnull = null;
		return listnull;
	}
	
	
	public boolean ajouterCp( int numCp, int solde, int mDv , int mDb, String etat, int cin  ) {
		
		try {
			pst= con.prepareStatement("insert into compte (numCompte,solde,montantDecouverte,montantDebit,etatCompte,cin)values(?,?,?,?,?,?)" );
			pst.setInt(1,numCp);
			pst.setInt(2,solde);
			pst.setInt(3, mDv);
			pst.setInt(4, mDb);
			pst.setString(5, etat);
			pst.setInt(6, cin);
			   
			return pst.execute();
			
		} catch (Exception e) {
			}
			
		finally {
			try {
				con.close();
			} catch (Exception e2) {
				
			}
		}
		return true;
		
	}
	
	public boolean ajouterCl( int c, String nom, String prenom , String mail  ) {
			
			try {
				pst= con.prepareStatement("insert into client (cin,nomClient,prenomClient,adresse)values(?,?,?,?)" );
				pst.setInt(1,c);
				pst.setString(2,nom);
				pst.setString(3,prenom);
				pst.setString(4,mail );
				
				return pst.execute();
				
			} catch (Exception e) {
			}
			
			finally {
				try {
					con.close();
				} catch (Exception e2) {
					
				}
			}
			return true;
			
		}
	public ObservableList<Compte> listercompte(){
		
		ObservableList<Compte> list = FXCollections.observableArrayList();
		try {
			pst= con.prepareStatement("select * from compte");
			res= pst.executeQuery();
			while (res.next()) {
				
				int numC = res.getInt("numCompte");
				int solde = res.getInt("solde");
				int montantDecouverte =res.getInt("montantDecouverte");
				int montantDebit = res.getInt("montantDebit");
				String etatCompte = res.getString("etatCompte");
				int cin = res.getInt("cin");
				Compte c = new Compte(numC, solde, montantDecouverte, montantDebit, etatCompte, cin);
				
				list.add(c);
							}
			
		} catch (Exception e) {
			
		}finally {
			try {
				con.close();
			} catch (Exception e) {
			
			}
		}
		return list;
		
		
	} 
	
	public ObservableList<Client> listerclient(){
		
		ObservableList<Client> list = FXCollections.observableArrayList();
		try {
			pst= con.prepareStatement("select * from client");
			res= pst.executeQuery();
			while (res.next()) {
				
				int cin = res.getInt("cin");
				String nomClient = res.getString("nomClient");
				String prenomClient = res.getString("prenomClient");
				String adresse = res.getString("adresse");
				
				Client c = new Client(cin, nomClient, prenomClient, adresse);
				list.add(c);
			}
			
		} catch (Exception e) {
			
		}finally {
			try {
				con.close();
			} catch (Exception e) {
			
			}
		}
		return list;
		
		
	}
	
	public boolean modifierCompte(int idcp,int dv , int db) {
		
		try {
			pst= con.prepareStatement("UPDATE compte SET montantDecouverte='"+ dv+"', montantDebit='"+db+"' WHERE numCompte = "+idcp);
					
			return !(pst.execute());
			
		} catch (Exception e) {
			
		}finally {
			try {
				con.close();
			} catch (Exception e) {
			
			}
		}
		
		return false;
	}
	public boolean modifierClient(int idcl,String n , String p) {
		
		try {
			pst= con.prepareStatement("UPDATE client SET nomClient='"+n+"', prenomClient='"+p+"' WHERE cin = "+idcl);
					
			return !(pst.execute());
			
		} catch (Exception e) {
			
		}finally {
			try {
				con.close();
			} catch (Exception e) {
			
			}
		}
		
		return false;
	}
	
	
	 public boolean supprimerCompte (int n ) {
		try {
			pst = con.prepareStatement("DELETE FROM compte where numCompte = "+n);
			return !(pst.execute());
		} catch (Exception e) {
			
		}finally {
			try {
				con.close();
			} catch (Exception e) {
			
			}
		}
		return false; 
	 }
	 public boolean supprimerClient (int n ) {
			try {
				pst = con.prepareStatement("DELETE FROM client where cin = "+n);
				return !(pst.execute());
			} catch (Exception e) {
				
			}finally {
				try {
					con.close();
				} catch (Exception e) {
				
				}
			}
			return false; 
		 }
	
	public boolean transactionsRetrait(int ncp , int mont) {
		try {
			pst = con.prepareStatement("select * from compte where numCompte="+ncp );
			res= pst.executeQuery();
			
				while(res.next()) {
					String query2 = "UPDATE compte set solde = ? where numCompte = ?";
					pst = con.prepareStatement(query2);
					pst.setInt(1, res.getInt("solde")-mont);
					pst.setInt(2, ncp);
					return !(pst.execute());
				}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}
	
	public boolean transactionsDebiter(int ncp , int mont) {
		try {
			pst = con.prepareStatement("select * from compte where numCompte="+ncp );
			res= pst.executeQuery();
				while(res.next()) {
					String query3 = "UPDATE compte set solde = ? where numCompte = ?";
					pst = con.prepareStatement(query3);
					pst.setInt(1, res.getInt("solde")+mont);
					pst.setInt(2, ncp);
					return !(pst.execute());
				}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}
	
	public ObservableList<Compte> maxSolde () {
		ObservableList<Compte> list = FXCollections.observableArrayList();
		try {
		
		
			pst = con.prepareStatement("select * from compte where solde = (select max(solde) from compte )");
			res= pst.executeQuery();
			while (res.next()) {
				
				int numC = res.getInt("numCompte");
				int solde = res.getInt("solde");
				int montantDecouverte =res.getInt("montantDecouverte");
				int montantDebit = res.getInt("montantDebit");
				String etatCompte = res.getString("etatCompte");
				int cin = res.getInt("cin");
				Compte c = new Compte(numC, solde, montantDecouverte, montantDebit, etatCompte, cin);
				
				list.add(c);
			}
		} catch (Exception e) {
			
		}
		return list;
	}
	
	public ObservableList<Compte> compteDecouverte(){
		ObservableList<Compte> list = FXCollections.observableArrayList();
		try {
			pst = con.prepareStatement("select * from compte where solde < 0 ");
			res = pst.executeQuery();
			while (res.next()) {
				int numC = res.getInt("numCompte");
				int solde = res.getInt("solde");
				int montantDecouverte =res.getInt("montantDecouverte");
				int montantDebit = res.getInt("montantDebit");
				String etatCompte = res.getString("etatCompte");
				int cin = res.getInt("cin");
				Compte c = new Compte(numC, solde, montantDecouverte, montantDebit, etatCompte, cin);
				
				list.add(c);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
		
	}
	
	public void situationDecouvert() {
		try {
			pst = con.prepareStatement("update compte set etatCompte =? where solde < ?");
			pst.setString(1, "decouvert");
			pst.setInt(2, 0);
		 
			pst.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
