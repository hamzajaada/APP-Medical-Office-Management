package DAO;

import java.util.List;
import Classes.RendezVous;

public interface RendezVousDao {
	List<RendezVous> getAllRVs();
	void addRV(RendezVous rv);
	void updateRV(RendezVous rv);
	void removeRV(long idrv);
	RendezVous getRV(long idrv);
}
