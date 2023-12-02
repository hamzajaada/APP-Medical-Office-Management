package DAO;

import java.util.List;
import Classes.Creneau;

public interface CreneauDao {
  List<Creneau> getAllCreneaus();
	void addCreneau(Creneau C);
	void updateCreneau(Creneau C);
	void removeCreneau(long id_client);
	Creneau getCreneau(long id_client);
}
