package dynamicprogramming;
/**
 * @author Kaan Irmak 220411831
 * @author Bengü Demireğen 220411791
 * @author Ahmet Karpuz 220411775
 */
public class Knapsack01 {
/**
 * Tablomuzu oluşturduktan sonra hangi eşyanın seçileceğini belirlemek için while döngüsü kullandık. Burada eşya sayısının negatif olamayacağını ve belirli bir kapasite değerinin olacağını bildiğimiz için i>0 ve w>0 koşulunu yazmamız gereklidir.
 * <br /> <br />
 * if (tablo[i][w] != tablo[i - 1][w]) : Bu kısımda değerin tekrar edilip edilmemesi kontrol edilir. 
 * Eğer değerimiz yukarıdan geldiyse o eşya atlanır ve “i--“ sayesinde bir sonraki eşyaya geçilir. Eğer değerimiz tekrar edilmemiş ve o eşyadan geldiyse o eşya seçilir, ekrana yazdırılır. O eşya seçildiğinden dolayı, eşyanın ağırlığını toplam kapasiteden çıkarmamız gerekmektedir. Bu metodumuz bu işlevleri sağlamaktadır.
 * @param kapasite
 * @param agirlik
 * @param deger
 * @param esyasayisi
 * @param tablo
 */
static void SecilenleriYazdir(int kapasite, int agirlik[], int deger[], int esyasayisi, int[][] tablo) {
		
        int w = kapasite;
        int i = esyasayisi;

        System.out.println("Seçilen eşyalar: ");
        System.out.println(); 						//1 satır boşluk için
        
        while (i > 0 && w > 0) 
        {
            if (tablo[i][w] != tablo[i - 1][w]) 
            {	System.out.println(i + ". eşya seçildi.");
                System.out.println("Eşyanın değeri: " + deger[i - 1] + " -- Eşyanın ağırlığı: " + agirlik[i - 1]);
                
                w = w - agirlik[i - 1];
            }
            i--;
        }
    }
/**
 * Bu metodumuz iki boyutlu dizileri kullanarak bir tablo oluşturmaktadır. İç içe for döngüsü kullanılarak iki boyutlu bir tablo oluşturulur. “i” değişkeni eşyaları temsil ederken “w” değişkeni ise ağırlık değerlerini temsil eder. If else yapıları dynamic programming yöntemini baz alarak tabloyu doldurma işlemini gerçekleştirir.
 * <br /><br />
 * Tablo oluşturulduktan sonra seçilen eşyaların yazdırılması için tüm bilgiler, SecilenleriYazdir() metodu içerisine yazılarak gönderilir. “return” kısmı ise main metodunda “sonuc” değişkenine atanmak üzere tablonun en büyük değerini gönderir. 
 * @param kapasite
 * @param agirlik
 * @param deger
 * @param esyasayisi
 * @return tablo[esyasayisi][kapasite]
 */
    static int DynamicProgramming(int kapasite, int agirlik[], int deger[], int esyasayisi) 
    {
        int[][] tablo = new int[esyasayisi + 1][kapasite + 1];

        for (int i = 0; i <= esyasayisi; i++) 
        {
            for (int w = 0; w <= kapasite; w++) 
            {
                if (i == 0 || w == 0)
                tablo[i][w] = 0;
                
                else if (agirlik[i - 1] <= w)   
                tablo[i][w] = Math.max(deger[i - 1] + tablo[i - 1][w - agirlik[i - 1]], tablo[i - 1][w]);
                
                else 
                tablo[i][w] = tablo[i - 1][w];
            }
        }

        SecilenleriYazdir(kapasite, agirlik, deger, esyasayisi, tablo);
        
        return tablo[esyasayisi][kapasite];
    }
/**
 * Main metodumuzda ise değer ve ağırlık dizilerimizi, istediğimiz parametreleri girerek oluştururuz. Girilen değerlerimizin sayısı, eşya sayımızın büyüklüğünü belirler. Bu değişkenleri DynamicProgramming() metoduna göndererek return değerini, “sonuç” değişkenine atarız. Atanan değer, tablodaki en büyük değerdir; bu da bize seçilen eşyaların toplam değerini vermektedir. Bu bilgileri ekrana yazdırarak gerekli işlemler sağlanır.
 */
    public static void main(String[] args) {
    	
        int deger[] = {1,3,9,5,6};
        int agirlik[] = {1,2,3,4,5};
        int kapasite = 8;
        int esyasayisi = deger.length;

        int sonuc = DynamicProgramming(kapasite, agirlik, deger, esyasayisi);
        System.out.println("Seçilen eşyaların toplam değeri: " + sonuc);
        System.out.println("-------------------------------------------");
        System.out.println("Kodu yazanlar: ");
        System.out.println();								//1 satır boşluk için
        System.out.println("Kaan Irmak 220411831");
        System.out.println("Bengü Demireğen 220411791");
        System.out.println("Ahmet Karpuz 220411775");
    }
}
