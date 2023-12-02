package DAO;

import java.util.List;
import Classes.Medecine;

public interface MedecineDao {
  List<Medecine> getAllMedecins();
	void addMedecin(Medecine M);
	void updateMedecin(Medecine M);
	void removeMedecin(long idm);
	Medecine getMedecin(long idm);
}