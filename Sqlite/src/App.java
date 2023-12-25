import java.util.List;

public class App {
  public static void main(String[] args) {
    /*
     * List<Notes> notes = NotasDAO.getNotes();
     * Notes myNote = NotasDAO.getNotesById(2);
     * NotasDAO.addNote("Quinta nota!", "Descrição da minha nota...");
     * NotasDAO.updateNote(2, "Titulo atualizado.", "Descrição atualizada.");
     * NotasDAO.dellNote(0);
     */
    List<Notes> notes = NotasDAO.getNotes();

    for (Notes note : notes) {
      System.out.println(note.title);
    }
  }
}