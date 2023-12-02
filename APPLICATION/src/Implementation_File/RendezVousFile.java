package Implementation_File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Classes.RendezVous;
import DAO.RendezVousDao;

public class RendezVousFile implements RendezVousDao, Serializable {
  private static final long serialVersionUID = 4L;

  @Override
  public List<RendezVous> getAllRVs() {
    List<RendezVous> rvs = new ArrayList<>();

    try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("rvs.ser"))) {
      rvs = (List<RendezVous>) objectIn.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return rvs;
  }

  @Override
  public void addRV(RendezVous rv) {
    List<RendezVous> rvs = getAllRVs();
    rvs.add(rv);
    saveRvs(rvs);
  }

  @Override
  public void updateRV(RendezVous rv) {
    List<RendezVous> rvs = getAllRVs();
    for (int i = 0; i < rvs.size(); i++) {
      if (rvs.get(i).getId() == rv.getId()) {
        rvs.set(i, rv);
        break;
      }
    }
    saveRvs(rvs);
  }

  @Override
  public void removeRV(long idrv) {
    List<RendezVous> rvs = getAllRVs();
    rvs.removeIf(rv -> rv.getId() == idrv);
    saveRvs(rvs);
  }

  @Override
  public RendezVous getRV(long idrv) {
    List<RendezVous> rvs = getAllRVs();
    for (RendezVous rv : rvs) {
      if (rv.getId() == idrv) {
        return rv;
      }
    }
    return null;
  }


  private static void saveRvs(List<RendezVous> rvs) {
    try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("rvs.ser"))) {
      objectOut.writeObject(rvs);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
