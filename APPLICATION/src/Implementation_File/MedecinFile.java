package Implementation_File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Classes.Medecine;
import DAO.MedecineDao;

public class MedecinFile implements MedecineDao, Serializable {
  private static final long serialVersionUID = 3L;

  @Override
  public List<Medecine> getAllMedecins() {
    List<Medecine> medecins = new ArrayList<>();

    try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("medecins.ser"))) {
      medecins = (List<Medecine>) objectIn.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return medecins;
  }

  @Override
  public void addMedecin(Medecine M) {
    List<Medecine> medecins = getAllMedecins();
    medecins.add(M);
    savemedecin(medecins);
  }

  @Override
  public void updateMedecin(Medecine M) {
    List<Medecine> medecins = getAllMedecins();
    for (int i = 0; i < medecins.size(); i++) {
      if (medecins.get(i).getId() == M.getId()) {
        medecins.set(i, M);
        break;
      }
    }
    savemedecin(medecins);
  }

  @Override
  public void removeMedecin(long idm) {
    List<Medecine> medecins = getAllMedecins();
    medecins.removeIf(medecin -> medecin.getId() == idm);
    savemedecin(medecins);
  }

  @Override
  public Medecine getMedecin(long idm) {
    List<Medecine> medecins = getAllMedecins();
    for (Medecine m : medecins) {
      if (m.getId() == idm) {
        return m;
      }
    }
    return null;
  }


  private static void savemedecin(List<Medecine> medecin) {
    try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("medecins.ser"))) {
      objectOut.writeObject(medecin);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}