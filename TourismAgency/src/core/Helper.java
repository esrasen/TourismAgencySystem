package core;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static void setTheme() {

        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }

    //Değerlendirme formu 24 - 25
    public static void showMsg(String str) {
        optionPaneTR();
        String msg;
        String title;
        switch (str) {
            case "fill":
                msg = "Lütfen tüm alanları doldurunuz!";
                title = "Hata!";
                break;
            case "done":
                msg = "İşlem başarılı!";
                title = "Sonuc!";
                break;
            case "notFound":
                msg = "Kayıt bulunamadı!";
                title = "Bulunamadı !";
                break;
            case "error":
                msg = "Bir hata oluştu!";
                title = "Hata!";
                break;
            case "unknownRole":
                msg = "Bilinmeyen kullanıcı tipi!";
                title = "Hata!";
                break;
            case "bedCountLimitExceeded":
                msg = "Yatak kapasitesi limiti aşıldı!";
                title = "Hata!";
                break;
            case "typeError":
                msg = "Doğru tipte veri giriniz!";
                title = "Hata!";
                break;
            case "stockError":
                msg = "Otelde yeterli oda yok!";
                title = "Hata!";
                break;
            case "checkIsBefore":
                msg = "Giriş tarihi sezon başlangıç tarihinden önce olamaz!";
                title = "Hata!";
                break;
            case "checkIsAfter":
                msg = "Çıkış tarihi sezon bitiş tarihinden sonra olamaz!";
                title = "Hata!";
                break;
            case "checkIsAfterCheckOut":
                msg = "Giriş tarihi çıkış tarihinden sonra olamaz!";
                title = "Hata!";
                break;
            case "noSeason":
                msg = "Sezon bulunamadı!";
                title = "Hata!";
                break;
            default:
                msg = str;
                title = "Mesaj";
        }

        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);

    }


    public static boolean confirm(String str) {
        optionPaneTR();
        String msg;

        if (str.equals("sure")) {
            msg = "Bu işlemi gerçekleştirmek istediğinize emin misiniz?";
        } else {
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null, msg, "Emin misin ?", JOptionPane.YES_NO_OPTION) == 0;
    }

    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        for (JTextField field : fieldList) {
            if (isFieldEmpty(field)) return true;
        }
        return false;
    }


    public static int getLocationPoint(String type, Dimension size) {
        switch (type) {
            case "x":
                return (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y":
                return (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default:
                return 0;
        }
    }

    public static void optionPaneTR() {
        UIManager.put("OptionPane.okButtonText", "Tamam");
        UIManager.put("OptionPane.yesButtonText", "Evet");
        UIManager.put("OptionPane.noButtonText", "Hayır");
        UIManager.put("OptionPane.cancelButtonText", "İptal");
    }
}
