import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

abstract class User implements ILoginable{

    private String username;
    private String password;
  
}

class Admin extends User {

}


class Company extends User {

}

class Customer {
    private String firstName;
    private String lastName;
    private String tc;

    // Parametreized constructor
    public Customer(String firstName, String lastName, String tc) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tc = tc;
        
    }

    // Getter ve Setter metotları
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTc() {
        return tc;
    }

    // toString metodu, sınıfın bilgilerini düzenli bir şekilde görüntülemek için kullanılır.
    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", tc='" + tc + '\'';
                
    }
}
abstract class Person {

    private String ad;
    private String soyad;
    public Person(String ad, String soyad) {
        this.ad = ad;
        this.soyad = soyad;
    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }
    @Override
    public String toString() {
        return "Ad: " + getAd() + "\nSoyad: " + getSoyad();
    }
}

class Personel extends Person {

    public Personel(String ad, String soyad) {
        super(ad, soyad);
    }
    // toString metodu: Sınıfın özelliklerini yazdırma
}

class Passenger extends Person {

    // Yolcu bilgileri
    public Passenger(String ad, String soyad) {
        super(ad, soyad);
    }
}

class Reservation {

    private Passenger passenger;
    private Arac araclar;
    private int koltukNo;

    public Reservation(Passenger passenger, Arac araclar, int koltukNo) {
        this.passenger = passenger;
        this.araclar = araclar;
        this.koltukNo = koltukNo;
    }

    // Reservation özelliklerine ait getter ve setter metotları
    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Arac getAraclar() {
        return araclar;
    }

    public void setArac(Arac araclar) {
        this.araclar = araclar;
    }

    public int getKoltukNo() {
        return koltukNo;
    }

    public void setKoltukNo(int koltukNo) {
        this.koltukNo = koltukNo;
    }

    // toString metodu: Sınıfın özelliklerini yazdırma
    @Override
    public String toString() {
        return "Yolcu Bilgileri:\n" + passenger.toString()
                + "\nAraç Bilgileri:\n" + araclar.toString()
                + "\nKoltuk Numarası: " + koltukNo;
    }
}

class Transport implements IReservable{

    private List<Company> companies;
    private List<Arac> araclar;
    private List<Sefer> seferler;
    private List<Reservation> reservations;
    private List<Seat> seats;

    public Transport() {
        this.companies = new ArrayList<>();
        this.araclar = new ArrayList<>();
        this.seferler = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.seats = new ArrayList<>();
    }

    public boolean isSeatAvailable(Sefer trip, int koltukNo) {
        for (Seat seat : seats) {
            if (seat.getTrip().equals(trip) && seat.getKoltukNo() == koltukNo) {
                return !seat.isReserved();
            }
        }
        return false;
    }

    public void reserveSeat(Sefer trip, int koltukNo) {
        for (Seat seat : seats) {
            if (seat.getTrip().equals(trip) && seat.getKoltukNo() == koltukNo) {
                seat.setReserved(true);
                break;
            }
        }
    }

    public void cancelSeatReservation(Sefer trip, int koltukNo) {
        for (Seat seat : seats) {
            if (seat.getTrip().equals(trip) && seat.getKoltukNo() == koltukNo) {
                seat.setReserved(false);
                break;
            }
        }
    }

    // İç içe sınıf (inner class): Koltuk bilgilerini temsil eden Seat sınıfı
    private static class Seat {

        private Sefer trip;
        private int koltukNo;
        private boolean reserved;

        public Seat(Sefer trip, int koltukNo) {
            this.trip = trip;
            this.koltukNo = koltukNo;
            this.reserved = false;
        }

        // Seat sınıfına ait getter ve setter metotları
        // (Bu metotları ihtiyaca göre doldurabilirsiniz.)
        public Sefer getTrip() {
            return trip;
        }

        public int getKoltukNo() {
            return koltukNo;
        }

        public boolean isReserved() {
            return reserved;
        }

        public void setReserved(boolean reserved) {
            this.reserved = reserved;
        }
    }
}

class Firma implements IProfitable{

    private String firmaAdi;
    private String kullaniciAdi;
    private String sifre;
    private List<Arac> araclar;
    private List<Sefer> seferler;

    public Firma(String firmaAdi, String kullaniciAdi, String sifre) {
        this.firmaAdi = firmaAdi;
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        this.araclar = new ArrayList<>();
        this.seferler = new ArrayList<>();
    }

    public void removeSefer(Sefer sefer) {
        seferler.remove(sefer);
    }

    public String getFirmaAdi() {
        return firmaAdi;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public List<Arac> getAraclar() {
        return araclar;
    }

    public List<Sefer> getSeferler() {
        return seferler;
    }

    public void addArac(Arac arac) {
        araclar.add(arac);
    }

    public void removeArac(Arac arac) {
        araclar.remove(arac);
    }

    public void addSefer(Sefer sefer) {
        seferler.add(sefer);
    }

    //FİRMA-SEFER
    public void showFirmaBilgileri() {
        System.out.println("Firma Adı: " + firmaAdi);
        System.out.println("Kullanıcı Adı: " + kullaniciAdi);
        System.out.println("Şifre: " + sifre);

        System.out.println("Araçlar:");
        for (Arac arac : araclar) {
            System.out.println("  - Araç ID: " + arac.getAracID() + ", Yakıt Türü: " + arac.getYakitTuru() + ", Kapasite: " + arac.getKapasite());
        }

        System.out.println("Seferler:");
        for (Sefer sefer : seferler) {
            System.out.println("  - Guzergah: " + sefer.getGuzergah() + ", Tip: " + sefer.getTip());
        }

        System.out.println();
    }
}

abstract class Arac {

    private String aracID;
    private String yakitTuru;
    private int kapasite;
    private Sefer tip;
    private double yakitTuketimi;

    public Arac(String aracID, String yakitTuru, int kapasite,double yakitTuketimi) {
        this.aracID = aracID;
        this.yakitTuru = yakitTuru;
        this.kapasite = kapasite;
        this.yakitTuketimi=yakitTuketimi;
    }

    // Araç özelliklerine ait getter ve setter metotları
    public String getAracID() {
        return aracID;
    }

    public void setAracID(String aracID) {
        this.aracID = aracID;
    }

    public String getYakitTuru() {
        return yakitTuru;
    }

    public void setYakitTuru(String yakitTuru) {
        this.yakitTuru = yakitTuru;
    }
    public double getYakitTuketimi() {
        return yakitTuketimi;
    }

    public int getKapasite() {
        return kapasite;
    }

    public void setKapasite(int kapasite) {
        this.kapasite = kapasite;
    }

    public Sefer getTip() {
        return tip;
    }

    public static Arac findAracById(List<Arac> araclar, String aracID) {
        for (Arac arac : araclar) {
            if (arac.getAracID().equals(aracID)) {
                return arac;
            }
        }
        return null; // Belirtilen aracID'ye sahip araç bulunamadı
    }

}

class Bus extends Arac {

    public Bus(String aracID, String yakitTuru, int kapasite,double yakitTuketimi) {
        super(aracID, yakitTuru, kapasite,yakitTuketimi);
    }
}

class Train extends Arac {

    public Train(String aracID, String yakitTuru, int kapasite,double yakitTuketimi) {
        super(aracID, yakitTuru, kapasite,yakitTuketimi);
    }
}

class Airplane extends Arac {

    public Airplane(String aracID, String yakitTuru, int kapasite,double yakitTuketimi) {
        super(aracID, yakitTuru, kapasite,yakitTuketimi);
    }
}

class Sefer {

    private String tripID;
    private Arac arac;
    private String tip;
    private String guzergah;
    private Date zaman;
    private Arac kullanilanArac;
    private String kullanilanAracID;
    private double fiyat;

    public Sefer(String guzergah, String tip) {
        this.guzergah = guzergah;
        this.tip = tip;
    }
    // Sefer özelliklerine ait getter ve setter metotları

    public String getTripID() {
        return tripID;
    }

    public void setTripID(String tripID) {
        this.tripID = tripID;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getGuzergah() {
        return guzergah;
    }

    public void setGuzergah(String guzergah) {
        this.guzergah = guzergah;
    }

    public Date getZaman() {
        return zaman;
    }

    public void setZaman(Date zaman) {
        this.zaman = zaman;
    }

    public double getFiyat() {
        return fiyat;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    public Arac getArac() {
        return arac;
    }

    public void setArac(Arac arac) {
        this.arac = arac;
    }

    public Arac getKullanilanArac() {
        return kullanilanArac;
    }

    public void setKullanilanArac(Arac kullanilanArac) {
        this.kullanilanArac = kullanilanArac;
    }

    public String getKullanilanAracID() {
        return kullanilanAracID;
    }

    public void setKullanilanAracID(String kullanilanAracID) {
        this.kullanilanAracID = kullanilanAracID;
    }

    public void belirleVeEkle(List<Arac> araclar, String aracID) {
        Arac arac = Arac.findAracById(araclar, aracID);
        if (arac != null) {
            setArac(arac);
            setKullanilanAracID(arac.getAracID());  // AracID'yi atama
        } else {
            System.out.println("Araç bulunamadı.");
        }
    }
    public String toString() {
        return "Guzergah: " + guzergah + ", Tip: " + tip;
    }

}

class Route {

    private String kalkisNoktasi;
    private String varisNoktasi;
    private double mesafe;

    public Route(String kalkisNoktasi, String varisNoktasi, double mesafe) {
        this.kalkisNoktasi = kalkisNoktasi;
        this.varisNoktasi = varisNoktasi;
        this.mesafe = mesafe;
    }
   

    public String getKalkisNoktasi() {
        return kalkisNoktasi;
    }

    public void setKalkisNoktasi(String kalkisNoktasi) {
        this.kalkisNoktasi = kalkisNoktasi;
    }

    public String getVarisNoktasi() {
        return varisNoktasi;
    }

    public void setVarisNoktasi(String varisNoktasi) {
        this.varisNoktasi = varisNoktasi;
    }

    public double getMesafe() {
        return mesafe;
    }

    public void setMesafe(double mesafe) {
        this.mesafe = mesafe;
    }

    // toString metodu: Sınıfın özelliklerini yazdırma
    @Override
    public String toString() {
        return "Kalkış Noktası: " + kalkisNoktasi + "\nVarış Noktası: " + varisNoktasi + "\nMesafe: " + mesafe + " km";
    }
}
       
class SehirFiyatlandirma {
    private Map<String, Map<String, Double>> karayoluFiyatlari;
    private Map<String, Map<String, Double>> havayoluFiyatlari;
    private Map<String, Map<String, Double>> demiryoluFiyatlari;

    public SehirFiyatlandirma() {
        this.karayoluFiyatlari = new HashMap<>();
        this.havayoluFiyatlari = new HashMap<>();
        this.demiryoluFiyatlari = new HashMap<>();

        // İller arasındaki fiyatları ekleyin
        String[] iller = new String[]{"Istanbul", "Kocaeli", "Bilecik", "Eskisehir", "Ankara", "Konya"};

        for (String sehir1 : iller) {
            karayoluFiyatlari.put(sehir1, new HashMap<>());
            havayoluFiyatlari.put(sehir1, new HashMap<>());
            demiryoluFiyatlari.put(sehir1, new HashMap<>());

            for (String sehir2 : iller) {
                if (!sehir1.equals(sehir2)) {
                    // İleride eklenebilecek farklı fiyatlar için örnek değerler
                    karayoluFiyatlari.get(sehir1).put(sehir2, 0.0);
                    havayoluFiyatlari.get(sehir1).put(sehir2, 0.0);
                    demiryoluFiyatlari.get(sehir1).put(sehir2, 0.0);
                }
            }
        }

        // Örnek fiyatları güncelleyin
        karayoluFiyatlari.get("Istanbul").put("Kocaeli", 100.0);
        karayoluFiyatlari.get("Istanbul").put("Ankara", 500.0);
        karayoluFiyatlari.get("Istanbul").put("Eskisehir", 300.0);
        karayoluFiyatlari.get("Istanbul").put("Konya", 600.0);
        karayoluFiyatlari.get("Kocaeli").put("İstanbul", 100.0);
        karayoluFiyatlari.get("Kocaeli").put("Eskisehir", 200.0);
        karayoluFiyatlari.get("Kocaeli").put("Ankara", 400.0);
        karayoluFiyatlari.get("Kocaeli").put("Konya", 500.0);
        karayoluFiyatlari.get("Eskisehir").put("Istanbul", 300.0);
        karayoluFiyatlari.get("Eskisehir").put("Kocaeli", 200.0);
        karayoluFiyatlari.get("Eskisehir").put("Konya", 300.0);
        karayoluFiyatlari.get("Ankara").put("Istanbul", 500.0);
        karayoluFiyatlari.get("Ankara").put("Kocaeli", 400.0);
        karayoluFiyatlari.get("Konya").put("Istanbul", 600.0);
        karayoluFiyatlari.get("Konya").put("Kocaeli", 500.0);
        karayoluFiyatlari.get("Konya").put("Eskisehir", 300.0);
        
        demiryoluFiyatlari.get("Istanbul").put("Kocaeli", 75.0);
        demiryoluFiyatlari.get("Istanbul").put("Bilecik", 225.0);
        demiryoluFiyatlari.get("Istanbul").put("Ankara", 375.0);
        demiryoluFiyatlari.get("Istanbul").put("Eskisehir", 300.0);
        demiryoluFiyatlari.get("Istanbul").put("Konya", 450.0);
        demiryoluFiyatlari.get("Kocaeli").put("Istanbul", 75.0);
        demiryoluFiyatlari.get("Kocaeli").put("Bilecik", 75.0);
        demiryoluFiyatlari.get("Kocaeli").put("Ankara", 300.0);
        demiryoluFiyatlari.get("Kocaeli").put("Eskisehir", 150.0);
        demiryoluFiyatlari.get("Kocaeli").put("Konya", 450.0);
        demiryoluFiyatlari.get("Bilecik").put("Istanbul", 225.0);
        demiryoluFiyatlari.get("Bilecik").put("Kocaeli", 75.0);
        demiryoluFiyatlari.get("Bilecik").put("Ankara", 225.0);
        demiryoluFiyatlari.get("Bilecik").put("Eskisehir", 75.0);
        demiryoluFiyatlari.get("Bilecik").put("Konya", 300.0);
        demiryoluFiyatlari.get("Ankara").put("Istanbul", 375.0);
        demiryoluFiyatlari.get("Ankara").put("Kocaeli", 300.0);
        demiryoluFiyatlari.get("Ankara").put("Bilecik", 225.0);
        demiryoluFiyatlari.get("Ankara").put("Eskisehir", 150.0);
        demiryoluFiyatlari.get("Eskisehir").put("Istanbul", 300.0);
        demiryoluFiyatlari.get("Eskisehir").put("Kocaeli", 150.0);
        demiryoluFiyatlari.get("Eskisehir").put("Bilecik", 75.0);
        demiryoluFiyatlari.get("Eskisehir").put("Ankara", 150.0);
        demiryoluFiyatlari.get("Eskisehir").put("Konya", 225.0);
        demiryoluFiyatlari.get("Konya").put("Istanbul", 450.0);
        demiryoluFiyatlari.get("Konya").put("Kocaeli", 350.0);
        demiryoluFiyatlari.get("Konya").put("Bilecik", 300.0);
        demiryoluFiyatlari.get("Konya").put("Eskisehir", 225.0);
        
        havayoluFiyatlari.get("Istanbul").put("Ankara", 250.0);
        havayoluFiyatlari.get("Istanbul").put("Konya", 300.0);
        havayoluFiyatlari.get("Konya").put("Istanbul", 300.0);
        havayoluFiyatlari.get("Ankara").put("Istanbul", 250.0);
    }

    public double getFiyat(String aracTipi, String nereden, String nereye) {
        Map<String, Map<String, Double>> fiyatMatrisi = getFiyatMatrisi(aracTipi);

        if (fiyatMatrisi.containsKey(nereden) && fiyatMatrisi.get(nereden).containsKey(nereye)) {
            return fiyatMatrisi.get(nereden).get(nereye);
        } else {
            // Belirtilen şehirler veya araç tipi bulunamadıysa varsayılan bir değer döndürün
            return -1.0; // veya başka bir değer
        }
    }

    private Map<String, Map<String, Double>> getFiyatMatrisi(String aracTipi) {
        switch (aracTipi) {
            case "Karayolu":
                return karayoluFiyatlari;
            case "Havayolu":
                return havayoluFiyatlari;
            case "Demiryolu":
                return demiryoluFiyatlari;
            default:
                throw new IllegalArgumentException("Geçersiz araç tipi: " + aracTipi);
        }
    }
}
class YakitFiyatlari {
    public static double getFirmaFiyati(String firma, String yakitTuru) {
        switch (firma) {
            case "A":
                return (yakitTuru.equals("Benzin")) ? 10.0 : 0.0; // Benzin var, diğerleri için 0
            case "B":
                return (yakitTuru.equals("Motorin")) ? 5.0 : 0.0; // Motorin var, diğerleri için 0
            case "C":
                if (yakitTuru.equals("Motorin")) {
                    return 6.0;
                } else if (yakitTuru.equals("Gaz")) {
                    return 25.0;
                } else {
                    return 0.0;
                }
            case "D":
                return (yakitTuru.equals("Elektrik")) ? 3.0 : 0.0; // Elektrik var, diğerleri için 0
            case "F":
                return (yakitTuru.equals("Gaz")) ? 20.0 : 0.0; // Gaz var, diğerleri için 0
            default:
                throw new IllegalArgumentException("Geçersiz firma: " + firma);
        }
    }
}
class SehirMesafeMatrisi {
    private Map<String, Map<String, Double>> karayoluMesafeleri;
    private Map<String, Map<String, Double>> demiryoluMesafeleri;
    private Map<String, Map<String, Double>> havayoluMesafeleri;

    public SehirMesafeMatrisi() {
        this.karayoluMesafeleri = new HashMap<>();
        this.demiryoluMesafeleri = new HashMap<>();
        this.havayoluMesafeleri = new HashMap<>();

        // Karayolu mesafelerini doldur
        karayoluMesafeleri.put("Istanbul", createMesafeMap1(0.0, 100.0, 500.0, 300.0, 600.0));
        karayoluMesafeleri.put("Kocaeli", createMesafeMap1(100.0, 0.0, 400.0, 200.0, 500.0));
        karayoluMesafeleri.put("Ankara", createMesafeMap1(500.0, 400.0, 0.0, 0.0, 0.0));
        karayoluMesafeleri.put("Eskisehir", createMesafeMap1(300.0, 200.0, 0.0, 0.0, 300.0));
        karayoluMesafeleri.put("Konya", createMesafeMap1(600.0, 500.0, 0.0, 300.0, 0.0));

        // Demiryolu mesafelerini doldur
        demiryoluMesafeleri.put("Istanbul", createMesafeMap2(0.0, 75.0, 225.0, 375.0, 300.0, 450.0));
        demiryoluMesafeleri.put("Kocaeli", createMesafeMap2(75.0, 0.0, 75.0, 300.0, 150.0, 350.0));
        demiryoluMesafeleri.put("Bilecik", createMesafeMap2(225.0, 75.0, 0.0, 225.0, 75.0, 300.0));
        demiryoluMesafeleri.put("Ankara", createMesafeMap2(375.0, 300.0, 225.0, 0.0, 150.0, 0.0));
        demiryoluMesafeleri.put("Eskisehir", createMesafeMap2(300.0, 150.0, 75.0, 150.0, 0.0, 225.0));
        demiryoluMesafeleri.put("Konya", createMesafeMap2(450.0, 350.0, 300.0, 0.0, 225.0, 0.0));

        // Havayolu mesafelerini doldur
        havayoluMesafeleri.put("Istanbul", createMesafeMap3(0.0, 250.0, 300.0));
        havayoluMesafeleri.put("Ankara", createMesafeMap3(250.0, 0.0, 0.0));
        havayoluMesafeleri.put("Konya", createMesafeMap3(300.0, 0.0, 0.0));
    }

    private Map<String, Double> createMesafeMap1(Double... mesafeler) {
    Map<String, Double> mesafeMap = new HashMap<>();
    String[] iller = {"Istanbul", "Kocaeli", "Ankara", "Eskisehir", "Konya"};

    if (iller.length == mesafeler.length) {
        for (int i = 0; i < mesafeler.length; i++) {
            mesafeMap.put(iller[i], mesafeler[i]);
        }
    } else {
        throw new IllegalArgumentException("İllere ait mesafe sayısı ile beklenen mesafe sayısı uyuşmuyor.");
    }

    return mesafeMap;
}
    private Map<String, Double> createMesafeMap2(Double... mesafeler) {
    Map<String, Double> mesafeMap = new HashMap<>();
    String[] iller = {"Istanbul", "Kocaeli", "Bilecik", "Ankara", "Eskisehir", "Konya"};

    if (iller.length == mesafeler.length) {
        for (int i = 0; i < mesafeler.length; i++) {
            mesafeMap.put(iller[i], mesafeler[i]);
        }
    } else {
        throw new IllegalArgumentException("İllere ait mesafe sayısı ile beklenen mesafe sayısı uyuşmuyor.");
    }

    return mesafeMap;
}

    private Map<String, Double> createMesafeMap3(Double... mesafeler) {
    Map<String, Double> mesafeMap = new HashMap<>();
    String[] iller = {"Istanbul", "Ankara", "Konya"};

    if (iller.length == mesafeler.length) {
        for (int i = 0; i < mesafeler.length; i++) {
            mesafeMap.put(iller[i], mesafeler[i]);
        }
    } else {
        throw new IllegalArgumentException("İllere ait mesafe sayısı ile beklenen mesafe sayısı uyuşmuyor.");
    }

    return mesafeMap;
}

    public double getMesafe(String aracTipi, String nereden, String nereye) {
        Map<String, Map<String, Double>> mesafeMatrisi = getMesafeMatrisi(aracTipi);

        if (mesafeMatrisi.containsKey(nereden) && mesafeMatrisi.get(nereden).containsKey(nereye)) {
            return mesafeMatrisi.get(nereden).get(nereye);
        } else {
            // Belirtilen şehirler veya araç tipi bulunamadıysa varsayılan bir değer döndürün
            return -1.0; // veya başka bir değer
        }
    }

    private Map<String, Map<String, Double>> getMesafeMatrisi(String aracTipi) {
        switch (aracTipi) {
            case "Karayolu":
                return karayoluMesafeleri;
            case "Demiryolu":
                return demiryoluMesafeleri;
            case "Havayolu":
                return havayoluMesafeleri;
            default:
                throw new IllegalArgumentException("Geçersiz araç tipi: " + aracTipi);
        }
    }
}

public class proLab extends javax.swing.JFrame {

    public proLab() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
     private static Firma loggedInFirma;
    private static String ADMIN_USERNAME = "a";
    private static String ADMIN_PASSWORD = "123";
    private static int HIZMET_BEDELI = 1000;
    private static Firma selectedFirma;
    private static Firma firm;
    private static List<Firma> firmalar = new ArrayList<>();
    private static List<Sefer> seferler = new ArrayList<>();
    private static List<Arac> araclar = new ArrayList<>();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private JComboBox<String> tipComboBox;
    private JComboBox<String> guzergahComboBox;

    public static void main(String args[]) {

        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();

            Firma firmaA = new Firma("A", "a", "123");
            Firma firmaB = new Firma("B", "b", "123");
            Firma firmaC = new Firma("C", "c", "123");
            Firma firmaD = new Firma("D", "d", "123");
            Firma firmaF = new Firma("F", "f", "123");

            Sefer sefer1 = new Sefer("Istanbul - Kocaeli - Bilecik - Eskisehir - Ankara - Eskisehir - Bilecik - Kocaeli - Istanbul (gidis-donus)", "Demiryolu");
            Sefer sefer2 = new Sefer("Istanbul - Kocaeli - Bilecik - Eskisehir - Konya - Eskisehir - Bilecik - Kocaeli - Istanbul (gidis-donus)", "Demiryolu");
            Sefer sefer22 = new Sefer("Istanbul - Kocaeli - Bilecik - Eskisehir - Konya - Eskisehir - Bilecik - Kocaeli - Istanbul (gidis-donus)", "Demiryolu");
            Sefer sefer3 = new Sefer("Istanbul - Kocaeli - Ankara - Kocaeli - Istanbul - Kocaeli - Ankara - Kocaeli - Istanbul (gidis-donus)", "Karayolu");
            Sefer sefer33 = new Sefer("Istanbul - Kocaeli - Ankara - Kocaeli - Istanbul - Kocaeli - Ankara - Kocaeli - Istanbul (gidis-donus)", "Karayolu");
            Sefer sefer333 = new Sefer("Istanbul - Kocaeli - Ankara - Kocaeli - Istanbul - Kocaeli - Ankara - Kocaeli - Istanbul (gidis-donus)", "Karayolu");
            Sefer sefer4 = new Sefer("Istanbul - Kocaeli - Eskisehir - Konya - Eskisehir - Kocaeli - Istanbul (gidis-donus)", "Karayolu");
            Sefer sefer44 = new Sefer("Istanbul - Kocaeli - Eskisehir - Konya - Eskisehir - Kocaeli - Istanbul (gidis-donus)", "Karayolu");
            Sefer sefer5 = new Sefer("Istanbul - Konya - Istanbul(gidis-donus)", "Havayolu");
            Sefer sefer55 = new Sefer("Istanbul - Konya - Istanbul(gidis-donus)", "Havayolu");
            Sefer sefer6 = new Sefer("Istanbul - Ankara -Istanbul (gidis-donus)", "Havayolu");
            Sefer sefer66 = new Sefer("Istanbul - Ankara -Istanbul (gidis-donus)", "Havayolu");
            
            Bus Abus1 = new Bus("IDabus1", "Benzin", 20,10.0);
            Bus Abus2 = new Bus("IDabus2", "Benzin", 15,10.0);
            Bus Bbus1 = new Bus("IDbbus1", "Motorin", 15,5.0);
            Bus Bbus2 = new Bus("IDbbus2", "Motorin", 20,5.0);
            Bus Cbus1 = new Bus("IDcbus1", "Motorin", 20,6.0);
            Airplane Cplane1 = new Airplane("IDcplane1", "Gaz", 30,25.0);
            Airplane Cplane2 = new Airplane("IDcplane2", "Gaz", 30,25.0);
            Train Dtrain1 = new Train("IDdtrain1", "Elektrik", 25,3.0);
            Train Dtrain2 = new Train("IDdtrain2", "Elektrik", 25,3.0);
            Train Dtrain3 = new Train("IDdtrain3", "Elektrik", 25,3.0);
            Airplane Fplane1 = new Airplane("IDfplane1", "Gaz", 30,20.0);
            Airplane Fplane2 = new Airplane("IDfplane2", "Gaz", 30,20.0);

            
            
            sefer3.setKullanilanAracID(Abus2.getAracID());
            sefer33.setKullanilanAracID(Bbus1.getAracID());
            sefer333.setKullanilanAracID(Abus1.getAracID());
            sefer2.setKullanilanAracID(Dtrain2.getAracID());
            sefer22.setKullanilanAracID(Dtrain3.getAracID());
            sefer1.setKullanilanAracID(Dtrain1.getAracID());
            sefer4.setKullanilanAracID(Cbus1.getAracID());
            sefer44.setKullanilanAracID(Bbus2.getAracID());
            sefer5.setKullanilanAracID(Cplane1.getAracID());
            sefer55.setKullanilanAracID(Cplane2.getAracID());
            sefer6.setKullanilanAracID(Fplane1.getAracID());
            sefer66.setKullanilanAracID(Fplane2.getAracID());
            
            seferler.add(sefer1);
            seferler.add(sefer2);
            seferler.add(sefer22);
            seferler.add(sefer3);
            seferler.add(sefer33);
            seferler.add(sefer333);
            seferler.add(sefer4);
            seferler.add(sefer44);
            seferler.add(sefer5);
            seferler.add(sefer55);
            seferler.add(sefer6);
            seferler.add(sefer66);
            

            araclar.add(Abus1);
            araclar.add(Abus2);
            araclar.add(Bbus1);
            araclar.add(Bbus2);
            araclar.add(Cbus1);
            araclar.add(Cplane1);
            araclar.add(Cplane2);
            araclar.add(Dtrain1);
            araclar.add(Dtrain2);
            araclar.add(Dtrain3);
            araclar.add(Fplane1);
            araclar.add(Fplane2);
            
            try {
                // Seferleri oluştururken tarih bilgisi ekleyin
                sefer1.setZaman(dateFormat.parse("04/12/2023"));
                sefer2.setZaman(dateFormat.parse("05/12/2023"));
                sefer3.setZaman(dateFormat.parse("06/12/2023"));
                sefer4.setZaman(dateFormat.parse("07/12/2023"));
                sefer5.setZaman(dateFormat.parse("08/12/2023"));
                sefer6.setZaman(dateFormat.parse("09/12/2023"));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            firmaA.addSefer(sefer3);
            firmaB.addSefer(sefer3);
            firmaB.addSefer(sefer4);
            firmaC.addSefer(sefer4);
            firmaC.addSefer(sefer5);
            firmaD.addSefer(sefer1);
            firmaD.addSefer(sefer2);
            firmaF.addSefer(sefer6);

            firmaA.addArac(Abus1);
            firmaA.addArac(Abus2);
            firmaB.addArac(Bbus1);
            firmaB.addArac(Bbus2);
            firmaC.addArac(Cbus1);
            firmaC.addArac(Cplane1);
            firmaC.addArac(Cplane2);
            firmaD.addArac(Dtrain1);
            firmaD.addArac(Dtrain2);
            firmaD.addArac(Dtrain3);
            firmaF.addArac(Fplane1);
            firmaF.addArac(Fplane2);

            // Firmaları listeye ekleme
            firmalar.add(firmaA);
            firmalar.add(firmaB);
            firmalar.add(firmaC);
            firmalar.add(firmaD);
            firmalar.add(firmaF);
            // Firma nesnesini oluşturduktan sonra

            for (Firma firma : firmalar) {
                firma.showFirmaBilgileri();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Rezervasyon Sistemi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        // Firma Girişi butonunu oluşturun ve panele ekleyin
        JButton firmaButton = new JButton("Firma Girişi");
        panel.add(firmaButton);

        // Admin Girişi butonunu oluşturun ve panele ekleyin
        JButton adminButton = new JButton("Admin Girişi");
        panel.add(adminButton);

        // Bilet Ara butonunu oluşturun ve panele ekleyin
        JButton biletAraButton = new JButton("Bilet Ara");
        panel.add(biletAraButton);

        firmaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFirmaGirisiEkran();
            }
        });
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAdminLogin();
            }
        });
        biletAraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBiletAraPanel();
            }
        });
        frame.setSize(600, 400);
        frame.setVisible(true);
    }

    private static void FirmaGirisiEkran() {
        JFrame firmaFrame = new JFrame("Firma Girişi");
        firmaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Firma Girişi için GUI öğelerini ekleyin.
        firmaFrame.setSize(600, 400);
        firmaFrame.setVisible(true);
    }

    private static void showAdminGirisiEkran() {
        JFrame adminFrame = new JFrame("Admin Girişi");
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Admin Girişi için GUI öğelerini ekleyin.
        adminFrame.setSize(600, 400);
        adminFrame.setVisible(true);
    }

    private static void showBiletAraEkran() {
        JFrame biletAraFrame = new JFrame("Bilet Ara");
        biletAraFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Bilet Ara için GUI öğelerini ekleyin.

        biletAraFrame.setSize(600, 400);
        biletAraFrame.setVisible(true);
    }

    //ADMİN İŞLEMLERİ
    private static void showAdminLogin() {
        JFrame adminLoginFrame = new JFrame("Admin Girişi");
        adminLoginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Kullanıcı Adı:");
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Şifre:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Giriş");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = usernameField.getText();
                char[] enteredPassword = passwordField.getPassword();
                String enteredPasswordString = new String(enteredPassword);

                if (enteredUsername.equals(ADMIN_USERNAME) && enteredPasswordString.equals(ADMIN_PASSWORD)) {
                    showAdminPanel();
                    adminLoginFrame.dispose(); // Admin Girişi penceresini kapat
                } else {
                    JOptionPane.showMessageDialog(adminLoginFrame, "Hatalı kullanıcı adı veya şifre!", "Hata", JOptionPane.ERROR_MESSAGE);
                }
                // Temizleme işlemi (güvenlik için)
                usernameField.setText("");
                passwordField.setText("");
            }
        });
        adminLoginFrame.getContentPane().add(panel);
        adminLoginFrame.setSize(300, 150);
        adminLoginFrame.setVisible(true);
    }

    private static void showAdminPanel() {
        JFrame adminFrame = new JFrame("Admin Paneli");
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton goruntuleButton = new JButton("Var Olan Firmaları Görüntüle");
        JButton yeniKayitButton = new JButton("Yeni Firma Kaydı Yap");
        JButton silButton = new JButton("Firma Kaydını Sil");
        JButton hizmetBedeliButton = new JButton("Hizmet Bedelini Belirle");
        JButton cikisButton = new JButton("Çıkış");

        panel.add(goruntuleButton);
        panel.add(yeniKayitButton);
        panel.add(silButton);
        panel.add(hizmetBedeliButton);
        panel.add(cikisButton);

        goruntuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Var olan firmaları görüntüleme işlemi
                if (firmalar.isEmpty()) {
                    JOptionPane.showMessageDialog(adminFrame, "Var olan firma bulunmamaktadır.");
                } else {
                    StringBuilder message = new StringBuilder("Var olan firmalar:\n");
                    for (Firma firma : firmalar) {
                        message.append(firma.getFirmaAdi()).append(" (Kullanıcı Adı: ").append(firma.getKullaniciAdi()).append(")\n");
                    }
                    JOptionPane.showMessageDialog(adminFrame, message.toString());
                }
            }
        });
        yeniKayitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firmaAdi = JOptionPane.showInputDialog(adminFrame, "Yeni firma adını girin:");
                String kullaniciAdi = JOptionPane.showInputDialog(adminFrame, "Kullanıcı adını girin:");
                String sifre = JOptionPane.showInputDialog(adminFrame, "Şifreyi girin:");

                if (firmaAdi != null && !firmaAdi.isEmpty() && kullaniciAdi != null && !kullaniciAdi.isEmpty() && sifre != null && !sifre.isEmpty()) {
                    Firma yeniFirma = new Firma(firmaAdi, kullaniciAdi, sifre);
                    firmalar.add(yeniFirma);
                    JOptionPane.showMessageDialog(adminFrame, "Yeni firma kaydı yapıldı: " + firmaAdi);
                } else {
                    JOptionPane.showMessageDialog(adminFrame, "Geçersiz giriş!", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        silButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Firma kaydını silme işlemi
                if (firmalar.isEmpty()) {
                    JOptionPane.showMessageDialog(adminFrame, "Silinecek firma bulunmamaktadır.");
                } else {
                    String silinecekFirmaAdi = JOptionPane.showInputDialog(adminFrame, "Silinecek firma adını girin:");

                    if (silinecekFirmaAdi != null && !silinecekFirmaAdi.isEmpty()) {
                        boolean firmaBulundu = false;
                        List<Firma> silinecekFirmalar = new ArrayList<>();

                        for (Firma firma : firmalar) {
                            if (firma.getFirmaAdi().equals(silinecekFirmaAdi)) {
                                silinecekFirmalar.add(firma);
                                firmaBulundu = true;
                            }
                        }
                        if (firmaBulundu) {
                            firmalar.removeAll(silinecekFirmalar);
                            JOptionPane.showMessageDialog(adminFrame, "Firma kaydı silindi: " + silinecekFirmaAdi);
                        } else {
                            JOptionPane.showMessageDialog(adminFrame, "Belirtilen isimde bir firma bulunamadı.", "Hata", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(adminFrame, "Geçersiz firma adı!", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        hizmetBedeliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String yeniHizmetBedeli = JOptionPane.showInputDialog(adminFrame, "Yeni hizmet bedelini girin:");
                try {
                    int yeniBedel = Integer.parseInt(yeniHizmetBedeli);
                    HIZMET_BEDELI = yeniBedel;
                    JOptionPane.showMessageDialog(adminFrame, "Hizmet bedeli belirlendi: " + HIZMET_BEDELI);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(adminFrame, "Geçersiz bir sayı girişi yaptınız.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        cikisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminFrame.dispose();  // Admin Paneli penceresini kapat
                createAndShowGUI();    // Rezervasyon Sistemi menüsünü göster
            }
        });

        adminFrame.getContentPane().add(panel);
        adminFrame.setSize(500, 300);
        adminFrame.setVisible(true);
    }

    //FİRMA GİRİŞİ KODLARI
    private static void showFirmaGirisiEkran() {
        JFrame firmaGirisiFrame = new JFrame("Firma Girişi");
        firmaGirisiFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel kullaniciAdiLabel = new JLabel("Kullanıcı Adı:");
        JTextField kullaniciAdiField = new JTextField();

        JLabel sifreLabel = new JLabel("Şifre:");
        JPasswordField sifreField = new JPasswordField();

        JButton girisButton = new JButton("Giriş");

        panel.add(kullaniciAdiLabel);
        panel.add(kullaniciAdiField);
        panel.add(sifreLabel);
        panel.add(sifreField);
        panel.add(new JLabel());
        panel.add(girisButton);

        girisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String girilenKullaniciAdi = kullaniciAdiField.getText();
                char[] girilenSifre = sifreField.getPassword();
                String girilenSifreString = new String(girilenSifre);

                // Firma bilgilerini kontrol et
                Firma girisYapanFirma = isValidFirma(girilenKullaniciAdi, girilenSifreString);
                if (girisYapanFirma != null) {
                    loggedInFirma = girisYapanFirma;
                    showFirmaPanel(girilenKullaniciAdi);
                    firmaGirisiFrame.dispose(); // Firma Girişi penceresini kapat
                } else {
                    JOptionPane.showMessageDialog(firmaGirisiFrame, "Hatalı firma bilgileri!", "Hata", JOptionPane.ERROR_MESSAGE);
                }

                // Temizleme işlemi (güvenlik için)
                kullaniciAdiField.setText("");
                sifreField.setText("");
                selectedFirma = getFirmaByCredentials(girilenKullaniciAdi, girilenSifreString);
                if (selectedFirma != null) {
                    showFirmaPanel(selectedFirma.getFirmaAdi());
                    firmaGirisiFrame.dispose(); // Firma Girişi penceresini kapat
                } else {
                    JOptionPane.showMessageDialog(firmaGirisiFrame, "Hatalı firma bilgileri!", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        firmaGirisiFrame.getContentPane().add(panel);
        firmaGirisiFrame.setSize(300, 200);
        firmaGirisiFrame.setVisible(true);
    }

    private static Firma isValidFirma(String kullaniciAdi, String sifre) {
        for (Firma firma : firmalar) {
            if (firma.getKullaniciAdi().equals(kullaniciAdi) && firma.getSifre().equals(sifre)) {
                return firma;
            }
        }
        return null;
    }

    private static void showFirmaPanel(String firmaAdi) {
        JFrame firmaPanelFrame = new JFrame("Firma Paneli - " + firmaAdi);
        firmaPanelFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        JButton aracEkleButton = new JButton("Araç Ekle");
        JButton aracSilButton = new JButton("Araç Sil");
        JButton seferEkleButton = new JButton("Sefer Ekle");
        JButton seferSilButton = new JButton("Sefer Sil");
        JButton aracGoruntuleButton = new JButton("Araçları Görüntüle");
        JButton maliyetHesaplaButton = new JButton("Maliyet Hesapla");
        JButton cikisButton = new JButton("Çıkış");

        panel.add(new JLabel("Hoş Geldiniz, " + firmaAdi + "!"));
        panel.add(aracGoruntuleButton);
        panel.add(seferEkleButton);
        panel.add(seferSilButton);
        panel.add(aracEkleButton);
        panel.add(aracSilButton);
        panel.add(maliyetHesaplaButton);
        panel.add(cikisButton);
        

        seferEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSeferEkleEkran();
            }
        });
        seferSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSeferSilEkran();
            }
        });

        aracGoruntuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Firma ile ilişkilendirilmiş araçları gösterme işlemi
                StringBuilder message = new StringBuilder("Firma " + firmaAdi + " Araçları:\n");
                for (Firma firma : firmalar) {
                    if (firma.getFirmaAdi().equals(firmaAdi)) {
                        for (Arac arac : firma.getAraclar()) {
                            message.append("  - Araç ID: ").append(arac.getAracID()).append(", Yakıt Türü: ").append(arac.getYakitTuru()).append(", Kapasite: ").append(arac.getKapasite()).append("\n");
                        }
                    }
                }
                JOptionPane.showMessageDialog(firmaPanelFrame, message.toString());
            }
        });
        aracEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Araç ekleme işlemi
                String aracID = JOptionPane.showInputDialog(firmaPanelFrame, "Araç ID girin:");
                String yakitTuru = JOptionPane.showInputDialog(firmaPanelFrame, "Yakıt Türü girin:");
                String kapasiteStr = JOptionPane.showInputDialog(firmaPanelFrame, "Kapasite girin:");
                String yakitTuketimiStr = JOptionPane.showInputDialog(firmaPanelFrame, "Yakıt maliyetini girin:");
                double yakitTuketimi =Integer.parseInt(yakitTuketimiStr);
                int kapasite = Integer.parseInt(kapasiteStr);
                // Girilen bilgilerle yeni bir araç oluşturun (bu sınıf ve metotları kendi kodunuza uyarlamalısınız)
                Arac yeniArac = new Arac(aracID, yakitTuru, kapasite,yakitTuketimi) {
                };

                // Firma ile ilişkilendirilmiş araç listesine yeni aracı ekleyin (bu sınıf ve metotları kendi kodunuza uyarlamalısınız)
                selectedFirma.addArac(yeniArac);

                // Kullanıcıya işlemin başarılı olduğuna dair bir mesaj gösterin
                JOptionPane.showMessageDialog(firmaPanelFrame, "Araç başarıyla eklendi!");
            }
        });
        maliyetHesaplaButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Firma ile ilişkilendirilmiş araçları gösterme işlemi
        StringBuilder message = new StringBuilder("Firma " + firmaAdi + " Araçları ve Maliyetler:\n");

        // Seçilen firmanın araç listesini al
        List<Arac> araclar = selectedFirma.getAraclar();
        SehirMesafeMatrisi sehirMesafeMatrisi = new SehirMesafeMatrisi();
        // Seçilen firmanın yakıt fiyatını al
        double yakitFiyati = YakitFiyatlari.getFirmaFiyati(selectedFirma.getFirmaAdi(), "Motorin");

        // Seçilen firmanın araçlarını döngü ile gez
        for (Arac arac : selectedFirma.getAraclar()) {
            // Aracın güzergahındaki mesafeyi hesapla (örneğin, İstanbul'dan Ankara'ya)
            double mesafe = sehirMesafeMatrisi.getMesafe("Karayolu", "Istanbul", "Ankara");

            // Aracın yakıt tüketimini al
            double yakitTuketimi = arac.getYakitTuketimi();

            // Maliyeti hesapla (mesafe * yakitTuketimi * yakitFiyati)
            double maliyet = mesafe * yakitTuketimi * yakitFiyati;

            // Mesajı oluştur ve ekrana göster
            message.append("  - Araç ID: ").append(arac.getAracID())
                   .append(", Mesafe: ").append(mesafe)
                   .append(", Yakıt Tüketimi: ").append(yakitTuketimi)
                   .append(", Maliyet: ").append(maliyet)
                   .append("\n");
        }

        JOptionPane.showMessageDialog(firmaPanelFrame, message.toString());
    }
});

        aracSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Araç silme işlemi
                String aracID = JOptionPane.showInputDialog(firmaPanelFrame, "Silmek istediğiniz aracın ID'sini girin:");

                // Firma listesinden seçilen firmanın referansını bulun
                Firma selectedFirma = findFirmaByName(firmalar, firmaAdi);

                // Seçilen firmanın araç listesinden aracı silin
                if (selectedFirma != null) {
                    Arac silinecekArac = findAracById(selectedFirma, aracID);
                    if (silinecekArac != null) {
                        selectedFirma.removeArac(silinecekArac);
                        // Kullanıcıya işlemin başarılı olduğuna dair bir mesaj gösterin
                        JOptionPane.showMessageDialog(firmaPanelFrame, "Araç başarıyla silindi!");
                    } else {
                        // Aracı bulunamadığına dair bir hata mesajı gösterin
                        JOptionPane.showMessageDialog(firmaPanelFrame, "Belirtilen ID'ye sahip araç bulunamadı!");
                    }
                } else {
                    // Firma bulunamadığına dair bir hata mesajı gösterin
                    JOptionPane.showMessageDialog(firmaPanelFrame, "Belirtilen isme sahip firma bulunamadı!");
                }
            }
        });
            
        cikisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firmaPanelFrame.dispose();  // Firma Paneli penceresini kapat
                createAndShowGUI();    // Rezervasyon Sistemi menüsünü göster
            }
        });
        

        firmaPanelFrame.getContentPane().add(panel);
        firmaPanelFrame.setSize(400, 200);
        firmaPanelFrame.setVisible(true);
    }


    public static Firma findFirmaByName(List<Firma> firmalar, String firmaAdi) {
        for (Firma firma : firmalar) {
            if (firma.getFirmaAdi().equals(firmaAdi)) {
                return firma;
            }
        }
        return null; // Belirtilen isme sahip firma bulunamazsa null döner
    }
// Araç ID'sine göre araç bulma fonksiyonu

    public static Arac findAracById(Firma firma, String aracID) {
        for (Arac arac : firma.getAraclar()) {
            if (arac.getAracID().equals(aracID)) {
                return arac;
            }
        }
        return null; // Belirtilen ID'ye sahip araç bulunamazsa null döner
    }

    private static void showAraclariGoruntulePanel() {
        JFrame araclarFrame = new JFrame("Araçları Görüntüle");
        araclarFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JComboBox<String> aracComboBox = new JComboBox<>();
        for (Arac arac : selectedFirma.getAraclar()) {
            aracComboBox.addItem(arac.getAracID());
        }

        JTextArea aracTextArea = new JTextArea();
        aracTextArea.setEditable(false);

        JButton gosterButton = new JButton("Göster");
        panel.add(aracComboBox);
        panel.add(gosterButton);
        panel.add(new JScrollPane(aracTextArea));

        gosterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String secilenAracID = (String) aracComboBox.getSelectedItem();
                Arac secilenArac = getAracByID(selectedFirma, secilenAracID);

                if (secilenArac != null) {
                    StringBuilder message = new StringBuilder("Araç Bilgileri:\n");
                    message.append("  - Araç ID: ").append(secilenArac.getAracID()).append("\n");
                    message.append("  - Yakıt Türü: ").append(secilenArac.getYakitTuru()).append("\n");
                    message.append("  - Kapasite: ").append(secilenArac.getKapasite()).append("\n\n");

                    message.append("Sefer Bilgileri:\n");
                    for (Sefer sefer : selectedFirma.getSeferler()) {
                        message.append("  - ").append(sefer.getGuzergah()).append(" - ").append(sefer.getTip()).append("\n");
                    }

                    aracTextArea.setText(message.toString());
                }
            }
        });

        araclarFrame.getContentPane().add(panel);
        araclarFrame.setSize(600, 400);
        araclarFrame.setVisible(true);
    }

    private static Firma getFirmaByCredentials(String kullaniciAdi, String sifre) {
        for (Firma firma : firmalar) {
            if (firma.getKullaniciAdi().equals(kullaniciAdi) && firma.getSifre().equals(sifre)) {
                return firma;
            }
        }
        return null;
    }

    private static Arac getAracByID(Firma firma, String aracID) {
        for (Arac arac : firma.getAraclar()) {
            if (arac.getAracID().equals(aracID)) {
                return arac;
            }
        }
        return null;
    }

    private static List<Sefer> getSeferlerByArac(Firma firma, Arac arac) {
        List<Sefer> aracaAitSeferler = new ArrayList<>();
        for (Sefer sefer : firma.getSeferler()) {
            if (sefer.getGuzergah().equals(arac)) {
                aracaAitSeferler.add(sefer);
            }
        }
        return aracaAitSeferler;
    }

    private static void showSeferEkleEkran() {
    JFrame seferEkleFrame = new JFrame("Sefer Ekle");
    seferEkleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(6, 2));
    JTextField guzergahField = new JTextField();
    JComboBox<String> tipComboBox = new JComboBox<>(new String[]{"Demiryolu", "Karayolu", "Havayolu"});

    // Firma araçlarını içeren bir JComboBox
    JComboBox<String> aracComboBox = new JComboBox<>();
    for (Arac arac : loggedInFirma.getAraclar()) {
        aracComboBox.addItem(arac.getAracID());
    }

    // Diğer eklenen kısım: Güzergah için seferleri ComboBox'a ekle
    JComboBox<String> seferComboBox = new JComboBox<>();
    for (Sefer sefer : loggedInFirma.getSeferler()) {
        seferComboBox.addItem(sefer.getGuzergah() + " - " + sefer.getTip());
    }

   tipComboBox.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Sefer tipine göre uygun güzergahları seçmek için bir metot çağırabilirsiniz.
        updateGuzergahComboBoxBasedOnTip((String) tipComboBox.getSelectedItem(), seferComboBox);
    }
});
    JButton seferEkleButton = new JButton("Sefer Ekle");
    panel.add(new JLabel("Tip:"));
    panel.add(tipComboBox);
    panel.add(new JLabel("Araç Seç:"));
    panel.add(aracComboBox);
    panel.add(new JLabel("Sefer Seç:"));
    panel.add(seferComboBox);
    panel.add(new JLabel()); // boş label
    panel.add(seferEkleButton);

    seferEkleButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Sefer bilgilerini al
            String guzergah = guzergahField.getText();
            String tip = (String) tipComboBox.getSelectedItem();
            String secilenGuzergah = (String) seferComboBox.getSelectedItem();
            String secilenAracID = (String) aracComboBox.getSelectedItem();
            Arac secilenArac = findAracById2(secilenAracID);

            // Yeni sefer oluştur
            Sefer yeniSefer = new Sefer(guzergah, tip);

            // loggedInFirma'ya seferi ekle
            loggedInFirma.getSeferler().add(yeniSefer);

            // ComboBox'ları güncelle
            updateGuzergahComboBoxBasedOnTip((String) tipComboBox.getSelectedItem(), seferComboBox);


            // Kullanıcıya bilgi mesajı göster
            JOptionPane.showMessageDialog(seferEkleFrame, "Sefer başarıyla eklendi!");
            StringBuilder message = new StringBuilder("Eklenen Sefer Bilgileri:\n");
            message.append("  - Güzergah: ").append(yeniSefer.getGuzergah()).append("\n");
            message.append("  - Tip: ").append(yeniSefer.getTip()).append("\n");
            message.append("  - Araç ID: ").append(secilenAracID).append("\n");

            JOptionPane.showMessageDialog(seferEkleFrame, message.toString());
            seferEkleFrame.dispose();  // Sefer Ekle penceresini kapat
        }
    });

    seferEkleFrame.getContentPane().add(panel);
    seferEkleFrame.setSize(900, 200);
    seferEkleFrame.setVisible(true);
}


   private static Arac findAracById2(String aracID) {
    for (Arac arac : loggedInFirma.getAraclar()) {
        if (arac.getAracID().equals(aracID)) {
            return arac;
        }
    }
    return null; // Arac bulunamazsa null döndür
}

    // Yeni eklenen metot: Sefer tipine göre güzergahları ComboBox'a ekle
   private static void updateGuzergahComboBoxBasedOnTip(String selectedTip, JComboBox<String> guzergahComboBox) {
    guzergahComboBox.removeAllItems();

    // Seçilen tipte seferleri bul
    List<Sefer> selectedTypeSeferler = loggedInFirma.getSeferler().stream()
            .filter(sefer -> sefer.getTip().equals(selectedTip))
            .collect(Collectors.toList());

    // Her bir seferin güzergahını ComboBox'a ekle
    for (Sefer sefer : selectedTypeSeferler) {
        guzergahComboBox.addItem(sefer.getGuzergah());
    }
}

    private static void showSeferSilEkran() {
        JFrame seferSilFrame = new JFrame("Sefer Sil");
        seferSilFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JComboBox<String> tipComboBox = new JComboBox<>(new String[]{"Demiryolu", "Karayolu", "Havayolu"});
        JComboBox<String> aracComboBox = new JComboBox<>();
        JComboBox<String> seferComboBox = new JComboBox<>();

        // Firma araçlarını içeren bir JComboBox
        for (Arac arac : loggedInFirma.getAraclar()) {
            aracComboBox.addItem(arac.getAracID());
        }

        tipComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Sefer tipine göre uygun araçları ve seferleri seçmek için bir metot çağırabilirsiniz.
                updateAracComboBoxBasedOnTip((String) tipComboBox.getSelectedItem(), aracComboBox);
                updateSeferComboBoxBasedOnTipAndArac((String) tipComboBox.getSelectedItem(),
                        (String) aracComboBox.getSelectedItem(), seferComboBox);
            }
        });

        aracComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Seçilen araca göre uygun seferleri seçmek için bir metot çağırabilirsiniz.
                updateSeferComboBoxBasedOnTipAndArac((String) tipComboBox.getSelectedItem(),
                        (String) aracComboBox.getSelectedItem(), seferComboBox);
            }
        });

        for (Sefer sefer : loggedInFirma.getSeferler()) {
            seferComboBox.addItem(sefer.getGuzergah() + " - " + sefer.getTip());
        }

        JButton silButton = new JButton("Sil");
        panel.add(new JLabel("Tip:"));
        panel.add(tipComboBox);
        panel.add(new JLabel("Araç Seç:"));
        panel.add(aracComboBox);
        panel.add(new JLabel("Sefer Seç:"));
        panel.add(seferComboBox);
        panel.add(new JLabel()); // boş label
        panel.add(silButton);

        silButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Seçilen seferi al
                String secilenSefer = (String) seferComboBox.getSelectedItem();

                if (secilenSefer != null) {
                    String[] secilenSeferParcalari = secilenSefer.split(" - ");

                    // Sefer bilgilerini kullanarak seferi listeden kaldır
                    Sefer sefer = new Sefer(secilenSeferParcalari[0], secilenSeferParcalari[1]);
                    loggedInFirma.getSeferler().remove(sefer);

                    // ComboBox'dan seçilen seferi kaldır
                    seferComboBox.removeItemAt(seferComboBox.getSelectedIndex());

                    JOptionPane.showMessageDialog(seferSilFrame, "Sefer başarıyla silindi!");
                } else {
                    JOptionPane.showMessageDialog(seferSilFrame, "Lütfen bir sefer seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        seferSilFrame.getContentPane().add(panel);
        seferSilFrame.setSize(900, 200);
        seferSilFrame.setVisible(true);
    }

    private static void updateSeferComboBoxBasedOnTipAndArac(String tip, String aracID, JComboBox<String> seferComboBox) {
        seferComboBox.removeAllItems();

        if (loggedInFirma != null) {
            for (Sefer sefer : loggedInFirma.getSeferler()) {
                Arac kullanilanArac = sefer.getKullanilanArac();

                // Tip kontrolü ve aracID kontrolü yapın
                if (kullanilanArac != null && kullanilanArac.getAracID().equals(aracID) && kullanilanArac.getTip().equals(tip)) {
                    seferComboBox.addItem(sefer.getGuzergah() + " - " + sefer.getTip());
                }
            }
        } else {
            System.out.println("Firma bilgileri veya sefer bulunamadı.");
        }
    }

    private static void updateAracComboBoxBasedOnTip(String tip, JComboBox<String> aracComboBox) {
        aracComboBox.removeAllItems();

        if (loggedInFirma != null) {
            for (Arac arac : loggedInFirma.getAraclar()) {
                // Tip kontrolü yapın
                if (arac instanceof Bus && tip.equals("Karayolu")) {
                    aracComboBox.addItem(arac.getAracID());
                } else if (arac instanceof Train && tip.equals("Demiryolu")) {
                    aracComboBox.addItem(arac.getAracID());
                } else if (arac instanceof Airplane && tip.equals("Havayolu")) {
                    aracComboBox.addItem(arac.getAracID());
                }
            }
        } else {
            System.out.println("Firma bilgileri veya araç bulunamadı.");
        }
    }

    //BİLET İŞLEMLERİ
    private static void showBiletAraPanel() {
    JFrame biletAraFrame = new JFrame("Bilet Ara");
    biletAraFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(6, 2));

    // Araç tipi seçim öğesi
    JLabel aracTipiLabel = new JLabel("Araç Tipi Seç:");
    JComboBox<String> aracTipiComboBox = new JComboBox<>(new String[]{"Karayolu", "Demiryolu", "Havayolu"});

    // Tarih seçim öğesi
    JLabel tarihLabel = new JLabel("Tarih Seç:");
    JSpinner tarihSpinner = new JSpinner(new SpinnerDateModel());
    JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(tarihSpinner, "dd/MM/yyyy");
    tarihSpinner.setEditor(dateEditor);
    tarihSpinner.setValue(new Date()); // Başlangıçta bugünün tarihini göster

    // Nereden ve Nereye seçim öğeleri
    JLabel neredenLabel = new JLabel("Nereden Seç:");
    JComboBox<String> neredenComboBox = new JComboBox<>(new String[]{"Istanbul","Kocaeli","Bilecik", "Eskisehir", "Ankara", "Konya"}); // İllerinizi buraya ekleyin
    JLabel nereyeLabel = new JLabel("Nereye Seç:");
    JComboBox<String> nereyeComboBox = new JComboBox<>(new String[]{"Istanbul","Kocaeli","Bilecik", "Eskisehir", "Ankara", "Konya"}); // İllerinizi buraya ekleyin

    // Bilet ara butonu
    JButton biletAraButton = new JButton("Bilet Ara");

    panel.add(aracTipiLabel);
    panel.add(aracTipiComboBox);
    panel.add(tarihLabel);
    panel.add(tarihSpinner);
    panel.add(neredenLabel);
    panel.add(neredenComboBox);
    panel.add(nereyeLabel);
    panel.add(nereyeComboBox);
    panel.add(new JLabel()); // boş label
    panel.add(biletAraButton);

    // Bilet ara butonuna tıklanınca yapılacak işlemler
    biletAraButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
 
            String selectedAracTipi = (String) aracTipiComboBox.getSelectedItem().toString();
            Date selectedDate = (Date) tarihSpinner.getValue();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = dateFormat.format(selectedDate);
            String selectedNereden = (String) neredenComboBox.getSelectedItem().toString();
            String selectedNereye = (String) nereyeComboBox.getSelectedItem().toString();

            // Seçilen tarih, nereden ve nereye bilgileri üzerinden araç ve sefer bilgilerini gösteren bir panel oluştur
            JPanel biletGosterPanel = createBiletGosterPanel(selectedAracTipi, formattedDate, selectedNereden, selectedNereye);

            // Bilet göster panelini yeni bir pencerede göster
            JFrame biletGosterFrame = new JFrame("Biletler - " + selectedAracTipi + " - " + formattedDate);
            biletGosterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            biletGosterFrame.getContentPane().add(biletGosterPanel);
            biletGosterFrame.setSize(600, 400);
            biletGosterFrame.setVisible(true);
        }
    });

    biletAraFrame.getContentPane().add(panel);
    biletAraFrame.setSize(400, 300);
    biletAraFrame.setVisible(true);
}
    private static JPanel createBiletGosterPanel(String selectedAracTipi, String formattedDate, String selectedNereden, String selectedNereye) {
    JPanel biletGosterPanel = new JPanel(new BorderLayout());

    // Sefer bilgilerini al
    List<Sefer> availableSeferler = getAvailableSeferler(selectedAracTipi, selectedNereden, selectedNereye);

    // Araç ve sefer bilgilerini gösteren tablo
    JTable biletTablo = createBiletTable(availableSeferler,selectedAracTipi);
    JScrollPane scrollPane = new JScrollPane(biletTablo);

    biletGosterPanel.add(scrollPane, BorderLayout.CENTER);
   
    SehirFiyatlandirma sehirFiyatlandirma = new SehirFiyatlandirma();
    
    // Koltuk seçim ve rezervasyon butonu
    JButton rezervasyonButton = new JButton("Rezervasyon Yap");
    rezervasyonButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Seçilen koltuğa rezervasyon yapma işlemleri burada yapılacak
        int selectedRow = biletTablo.getSelectedRow();
        if (selectedRow != -1) {
            String aracID = (String) biletTablo.getValueAt(selectedRow, 0);
            String seferGuzergah = (String) biletTablo.getValueAt(selectedRow, 1);

            // Seçilen aracın kapasitesini al
            int kapasite = getAracKapasite(aracID);
            
            if (selectedNereden.equals(selectedNereye)) {
                        JOptionPane.showMessageDialog(biletGosterPanel, "Nereden ve Nereye aynı olamaz. Lütfen tekrar seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
                    } else {
                        // Koltuk seçim panelini göster
                        showKoltukSecPanel(aracID, kapasite,sehirFiyatlandirma,selectedNereden,selectedNereye,selectedAracTipi);
                        }
        } else {
            // Sefer seçilmediğinde uyarı ver
            //JOptionPane.showMessageDialog(biletGosterFrame, "Lütfen bir sefer seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
        }
    }
});
    biletGosterPanel.add(rezervasyonButton, BorderLayout.SOUTH);

    return biletGosterPanel;
}
    private static int getAracKapasite(String aracID) {
    // Burada araçlar listesini kontrol ederek, verilen aracID'ye sahip aracın kapasitesini bulun
    // Örneğin, araclar listesi Arac nesnelerini içeriyorsa:
    for (Arac arac : araclar) {
        if (arac.getAracID().equals(aracID)) {
            return arac.getKapasite();
        }
    }
    return 0; // Bulunamadıysa varsayılan olarak 0 döndürülebilir veya başka bir değer atanabilir
}

    private static List<Sefer> getAvailableSeferler(String selectedAracTipi, String selectedNereden, String selectedNereye) {
    List<Sefer> availableSeferler = new ArrayList<>();

    for (Sefer sefer : seferler) {
        // Seferin tarihi null değilse ve seçilen tarih ile eşleşiyorsa listeye ekle
        if (sefer.getTip() != null && sefer.getTip().equals(selectedAracTipi)
                && sefer.getGuzergah().contains(selectedNereden) && sefer.getGuzergah().contains(selectedNereye)) {
            belirleVeEkleArac(sefer, araclar,selectedAracTipi);
            availableSeferler.add(sefer);
        }
    }

    return availableSeferler;
}

    private static void belirleVeEkleArac(Sefer sefer, List<Arac> araclar, String selectedAracTipi) {
        // Araç tipine uygun bir araç bul
        for (Arac arac : araclar) {
            if (arac.getTip() != null && arac.getTip().equals(selectedAracTipi)) {
                // Seçilen araç tipine ait aracı belirle
                sefer.setKullanilanAracID(arac.getAracID());
                break; // Bir araç bulunduğunda döngüden çık
            }
        }
    }

    // Araç ve sefer bilgilerini gösteren tabloyu oluşturan metot
    // Tabloyu oluşturan metodun güncellenmiş versiyonu
    private static JTable createBiletTable(List<Sefer> seferler, String selectedAracTipi) {
        String[] columnNames = {"Araç ID", "Güzergah"};
        String[][] data = new String[seferler.size()][4];

        for (int i = 0; i < seferler.size(); i++) {
            Sefer sefer = seferler.get(i);
            data[i][0] = sefer.getKullanilanAracID();
            data[i][1] = sefer.getGuzergah();
        }

        return new JTable(new DefaultTableModel(data, columnNames));
    }

    

    private static void showKoltukSecPanel(String aracID, int kapasite,SehirFiyatlandirma sehirFiyatlandirma,String selectedNereden, String selectedNereye,String selectedAracTipi) {
    JFrame koltukSecFrame = new JFrame("Koltuk Seç");
    koltukSecFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1, 2));

    // Koltuk Listesi
    DefaultListModel<String> koltukListModel = new DefaultListModel<>();
    JList<String> koltukList = new JList<>(koltukListModel);

    Set<Integer> satinalinanKoltuklar = new HashSet<>();
    Set<Integer> doluKoltuklar = new HashSet<>();
    Random random = new Random();

    for (int i = 0; i < kapasite; i++) {
        String koltuk = "Koltuk " + (i + 1);

        // Rastgele olarak bazı koltukları dolu yap
        if (random.nextBoolean()) {
            koltukListModel.addElement(koltuk + " (DOLU)");
            doluKoltuklar.add(i);
        } else {
            koltukListModel.addElement(koltuk);
        }
    }

    koltukList.setSelectionModel(new DefaultListSelectionModel() {
        @Override
        public void setSelectionInterval(int index0, int index1) {
            // Dolu koltukları seçilemez yap
            if (doluKoltuklar.contains(index0)) {
                return;
            }
            super.setSelectionInterval(index0, index1);
        }
    });

    JScrollPane koltukScrollPane = new JScrollPane(koltukList);
    panel.add(koltukScrollPane);

    // Bilgi Girişi Paneli
    JPanel bilgiPanel = new JPanel();
    bilgiPanel.setLayout(new GridLayout(4, 2));

    JTextField isimField = new JTextField();
    JTextField soyisimField = new JTextField();
    JTextField mailField = new JTextField();

    bilgiPanel.add(new JLabel("İsim: "));
    bilgiPanel.add(isimField);
    bilgiPanel.add(new JLabel("Soyisim: "));
    bilgiPanel.add(soyisimField);
    bilgiPanel.add(new JLabel("Mail: "));
    bilgiPanel.add(mailField);

    // Satın Al butonu
    JButton satinAlButton = new JButton("Satın Al");
    satinAlButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (koltukList.isSelectionEmpty()) {
                JOptionPane.showMessageDialog(koltukSecFrame, "Lütfen en az bir koltuk seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            } else if (isTextFieldEmpty(isimField) || isTextFieldEmpty(soyisimField) || isTextFieldEmpty(mailField)) {
                JOptionPane.showMessageDialog(koltukSecFrame, "Lütfen tüm bilgileri doldurun.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            } else {
                int[] selectedIndices = koltukList.getSelectedIndices();
                double toplamOdeme = 0.0;
                String isim = isimField.getText();
                String soyisim = soyisimField.getText();
                String mail = mailField.getText();

                StringBuilder bilgiler = new StringBuilder("Müşteri Bilgileri:\n");
                bilgiler.append("İsim: ").append(isim).append("\n");
                bilgiler.append("Soyisim: ").append(soyisim).append("\n");
                bilgiler.append("Mail: ").append(mail).append("\n");
                // Seçilen şehirlerin arasındaki fiyatları topla
                double sehirFiyati = sehirFiyatlandirma.getFiyat(selectedAracTipi,selectedNereden, selectedNereye);

                for (int index : selectedIndices) {
                    satinalinanKoltuklar.add(index);
                    koltukListModel.setElementAt(koltukListModel.getElementAt(index) + " (DOLU)", index);
                    doluKoltuklar.add(index);
                    toplamOdeme += sehirFiyati; // Her seçilen koltuk için sehirFiyati kadar ekle
                }

                // Bilet satışı tamamlandıktan sonra toplam ödemeyi göster
                JOptionPane.showMessageDialog(koltukSecFrame, "Bilet satın alındı. Toplam Ödeme: " + toplamOdeme + " TL\n\n"+bilgiler.toString(), "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });

    bilgiPanel.add(satinAlButton);

    panel.add(bilgiPanel);

    koltukSecFrame.getContentPane().add(panel);
    koltukSecFrame.setSize(600, 300);
    koltukSecFrame.setVisible(true);
}

    private static boolean isTextFieldEmpty(JTextField textField) {
        return textField.getText().trim().isEmpty();
    }

}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

