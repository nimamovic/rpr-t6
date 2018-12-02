package ba.unsa.etf.rpr.tutorijal6;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    public ComboBox mjesto;
    public ComboBox status;
    public ComboBox godina;
    public ComboBox smjer;
    public CheckBox pripadnost;
    public TextField ime;
    public TextField prezime;
    public TextField index;
    public TextField jmbg;
    public TextField datum;
    public TextField email;
    public TextField adresa;
    public TextField telefon;
    private boolean imeValidno;
    private boolean prezimeValidno;
    private boolean indeksValidan;
    private boolean jmbgValidno;
    private boolean datumValidno;
    private boolean emailValidno;
    private boolean telefonValidno;
    public String uporediSaJmbg;
    public String datumZaIspis = "";
    public String uporeditiDatum = "";

    public boolean formularValidan() {
        return (imeValidno && prezimeValidno && indeksValidan && jmbgValidno && datumValidno && emailValidno);
    }

    private boolean ispravnoImeiPrezime(String n) {
        if (n.length() < 1 || n.length() > 20) return false;
        for (int i = 0; i < n.length(); i++) {
            if (!(n.charAt(i) >= 'A' && n.charAt(i) <= 'Z') && !(n.charAt(i) >= 'a' && n.charAt(i) <= 'z'))
                return false;
        }
        return !n.trim().isEmpty();
    }

    private boolean ispravanDatum(String n) {
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) >= '0' && n.charAt(i) <= '9') {
                for (int j = 0; j < n.length(); j++) if(n.charAt(j) == '/' || n.charAt(j) == '.') return true;
            }
        }
        return false;
    }

    private boolean ispravanJMBG(String n) {
        /* ne radi ipak
        if (n.length() != 13) return false;
        if(11-((((n.charAt(0)-'0')+(n.charAt(6)-'0'))*7+6*((n.charAt(1)-'0')+(n.charAt(7)-'0'))+5*((n.charAt(2)-'0')+(n.charAt(8)-'0'))+4*((n.charAt(3)-'0')+(n.charAt(9)-'0'))+3*((n.charAt(4)-'0')+(n.charAt(10)-'0'))+2*((n.charAt(5)-'0')+(n.charAt(11)-'0')))%11)==n.charAt(12)) return  true;
        return false;*/
        if (n.length() < 13 || n.length() > 13) return false;
        uporeditiDatum = n.substring(0, 6);
        int regija = (n.charAt(7) - '0') * 10 + (n.charAt(8) - '0');
        if (regija < 0 || regija > 96) return false;
        int jedinstveniBroj = (n.charAt(9) - '0') * 100 + (n.charAt(10) - '0') * 10 + (n.charAt(11) - '0');
        if (!((jedinstveniBroj>  0 &&jedinstveniBroj <= 499)||(jedinstveniBroj>=500 &&jedinstveniBroj<=999))) return false;
        int kontrolnaCifra = 11 - ((7 * ((n.charAt(0) - '0') + (n.charAt(6) - '0')) + 6 * ((n.charAt(1) - '0') + (n.charAt(7) - '0')) + 5 * ((n.charAt(2) - '0') + (n.charAt(8) - '0')) + 4 * ((n.charAt(3) - '0') + (n.charAt(9) - '0')) + 3 * ((n.charAt(4) - '0') + (n.charAt(10) - '0')) + 2 * ((n.charAt(5) - '0') + (n.charAt(11) - '0'))) % 11);
        if (kontrolnaCifra > 9) kontrolnaCifra = 0;
        if (kontrolnaCifra != (n.charAt(12) - '0')) return false;
        return true;
    }

    private boolean ispravanEmail(String n) {
        for (int i = 0; i < n.length(); i++) if (n.charAt(i) == '@') return true;
        return false;
    }

    private boolean ispravanIndex(String n) {
        if (n.length() > 5) return false;
        for (int i = 0; i < n.length(); i++) if (!(n.charAt(i) >= '0' && n.charAt(i) <= '9')) return false;
        return !n.trim().isEmpty();
    }
    private boolean ispravanTelefon(String n){
        Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
        Pattern pattern1 = Pattern.compile("\\d{3}-\\d{6}");
        Matcher matcher = pattern.matcher(n);
        Matcher matcher1 = pattern1.matcher(n);
        return (matcher.matches()|| matcher1.matches());
    }

    @FXML
    public void initialize() {
        imeValidno = false;
        prezimeValidno = false;
        indeksValidan = false;
        jmbgValidno = false;
        datumValidno = false;
        emailValidno = false;
        ime.getStyleClass().add("nijeOkej");
        prezime.getStyleClass().add("nijeOkej");
        index.getStyleClass().add("nijeOkej");
        jmbg.getStyleClass().add("nijeOkej");
        datum.getStyleClass().add("nijeOkej");
        email.getStyleClass().add("nijeOkej");

        ime.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (ispravnoImeiPrezime(n)) {
                    ime.getStyleClass().removeAll("nijeOkej");
                    ime.getStyleClass().add("okej");
                    imeValidno = true;
                } else {
                    ime.getStyleClass().removeAll("okej");
                    ime.getStyleClass().add("nijeOkej");
                    imeValidno = false;
                }
            }
        });
        prezime.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (ispravnoImeiPrezime(n)) {
                    prezime.getStyleClass().removeAll("nijeOkej");
                    prezime.getStyleClass().add("okej");
                    prezimeValidno = true;
                } else {
                    prezime.getStyleClass().removeAll("okej");
                    prezime.getStyleClass().add("nijeOkej");
                    prezimeValidno = false;
                }
            }
        });
        index.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (ispravanIndex(n)) {
                    index.getStyleClass().removeAll("nijeOkej");
                    index.getStyleClass().add("okej");
                    indeksValidan = true;
                } else {
                    index.getStyleClass().removeAll("okej");
                    index.getStyleClass().add("nijeOkej");
                    indeksValidan = false;
                }
            }
        });
        jmbg.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (ispravanJMBG(n)) {
                    jmbg.getStyleClass().removeAll("nijeOkej");
                    jmbg.getStyleClass().add("okej");
                    jmbgValidno = true;
                } else {
                    jmbg.getStyleClass().removeAll("okej");
                    jmbg.getStyleClass().add("nijeOkej");
                    jmbgValidno = false;
                }
            }
        });
        datum.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (ispravanDatum(n)) {
                    datum.getStyleClass().removeAll("nijeOkej");
                    datum.getStyleClass().add("okej");
                    datumValidno = true;
                } else {
                    datum.getStyleClass().removeAll("okej");
                    datum.getStyleClass().add("nijeOkej");
                    datumValidno = false;
                }
            }
        });
        email.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (ispravanEmail(n)) {
                    email.getStyleClass().removeAll("nijeOkej");
                    email.getStyleClass().add("okej");
                    emailValidno = true;
                } else {
                    email.getStyleClass().removeAll("okej");
                    email.getStyleClass().add("nijeOkej");
                    emailValidno = false;
                }
            }
        });

    }


    public void potvrda(javafx.event.ActionEvent actionEvent) {
        String rod = mjesto.getEditor().getText();
        String imeNovo = ime.getText();
        String prezimeNovo = prezime.getText();
        String datumNovo = datum.getText();
        String jmbgNovi = jmbg.getText();
        String izdvojiDatum = "";
        if (ispravanDatum(datumNovo)) {
            datumValidno = true;
        } else {
            datumValidno = false;
            datum.getStyleClass().add("nijeOkej");
        }
        if(ispravanJMBG(jmbgNovi)) jmbgValidno=true;
        String emailNovi = email.getText();
        if (ispravanEmail(emailNovi)) {
            emailValidno = true;

        } else {
            emailValidno = false;
            email.getStyleClass().add("nijeOkej");
        }
        telefonValidno=ispravanTelefon(telefon.getText());
        if(telefonValidno)telefon.getStyleClass().add("okej");
        if (formularValidan()) {
            System.out.println("Student: " + imeNovo + " " + prezimeNovo + " ( " + index.getText() + " )");
            System.out.println("JMBG: " + jmbgNovi + ", datum rođenja: " + datumZaIspis +", mjesto rodđenja: "+ rod);
            System.out.println("Ulica stanovanja: " + adresa.getText() + ",broj telefona: " + telefon.getText());
            System.out.println("Email adresa: " + emailNovi);
            System.out.println(status.getValue().toString() + " student, smjer: " + smjer.getValue().toString() + " godina: " + godina.getValue());
            if (pripadnost.isSelected()) System.out.println("Postoji neka od boračke pripadnosti");
            else System.out.println("Ne postoji nikakva boračka pripadnost");
        }
        if (!formularValidan()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nije validno!");
            alert.setHeaderText("Popunjeni podaci nisu validni!");
            alert.setContentText("Polja označena sa crvenom bojom nisu validna, molimo Vas da ispravino popunite formular!");
            alert.show();
        }
    }
}
