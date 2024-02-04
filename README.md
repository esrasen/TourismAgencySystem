<h1 align="center">Turizm Acentesi Yönetim Sistemi</h1>
<h3 align="center">Turizm sektöründe faaliyet gösteren işletmeler için rezervasyon sistemi</h3>  


<br/>


<p align="center"> 
  <img src="docs/images/main.png" alt="Sistem Ana Görseli" width="60%" height="30%">
</p>

<!-- TABLE OF CONTENTS -->
<h2 id="table-of-contents"> :book: İçerik</h2>

<details open="open">
  <summary>İçerik</summary>
  <ol>
    <li><a href="#proje-ozeti"> ➤ Proje Özeti</a></li>
    <li><a href="#teknolojiler"> ➤ Kullanılan Teknolojiler</a></li>
    <li><a href="#kurulum"> ➤ Kurulum ve Başlatma</a></li>
    <li><a href="#kullanici-yonetimi"> ➤ Kullanıcı Yönetimi</a></li>
    <li><a href="#otel-yonetimi"> ➤ Otel Yönetimi</a></li>
    <li><a href="#oda-yonetimi"> ➤ Oda Yönetimi</a></li>
    <li><a href="#rezervasyon-islemleri"> ➤ Rezervasyon İşlemleri</a></li>
    <li><a href="#sistem-gorselleri"> ➤ Program İçi Görseller</a></li>
    <li><a href="#iletisim"> ➤ İletişim</a></li>
  </ol>
</details>


![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

<!-- VİDEO -->
<h2 id="proje-ozeti-video-kaydı"> :video_camera: Proje Özeti Video Kaydı</h2>

<p>
✤ <a href="https://drive.google.com/file/d/1oGhAhnkF8Km8NiwUC_Qcac16mK33Jadn/view?usp=sharing">Video Linki</a> <br>
</p>

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)
<!-- PROJECT OVERVIEW -->
<h2 id="proje-ozeti"> :mag_right: Proje Özeti</h2>

<p align="justify"> 
Patika Turizm Acentesi için geliştirilen bu yönetim sistemi, turizm sektöründe faaliyet gösteren işletmelerin günlük operasyonlarını daha etkili bir şekilde yönetmelerini sağlamak amacıyla tasarlanmıştır. Sistem, otel rezervasyon süreçlerini optimize ederek, acente çalışanlarının müşteri taleplerine hızlı ve etkili bir şekilde yanıt vermesine olanak tanır. Admin ve acente çalışanı olmak üzere iki farklı kullanıcı tipi tanımlanmış, bu kullanıcıların yetkileri ile sistem üzerinde çeşitli işlemleri gerçekleştirmeleri sağlanmıştır. Otel ve oda yönetimi, dönem ve fiyat yönetimi, oda arama ve rezervasyon işlemleri gibi temel özelliklerle, acentenin işlerini dijital ortamda kolayca yürütmesi hedeflenmiştir.
</p>

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)
<!-- USED TECHNOLOGIES -->
<h2 id="teknolojiler"> :computer: Kullanılan Teknolojiler</h2>

<p align="justify"> 
Projede kullanılan teknolojiler:

[![made-with-java](https://img.shields.io/badge/Made%20with-Java-green.svg)](https://www.python.org/) <br>
[![made-with-Swing](https://img.shields.io/badge/Made%20with-Swing-red.svg)](https://www.python.org/) <br>
[![made-with-ide](https://img.shields.io/badge/IntelliJ%20IDEA%20%2F%20Eclipse%20%2F%20Herhangi%20bir%20Java%20-IDE-blue.svg)](https://www.python.org/) <br>

</p>

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)
<!-- SETUP AND LAUNCH -->
<h2 id="kurulum"> :hammer: Kurulum ve Başlatma</h2>

<p align="justify"> 
Projeyi  klonlamak ve çalıştırmak için aşağıdaki adımları takip edin:

```bash
git clone https://github.com/esrasen/TourismAgencySystem.git
cd TourismAgencySystem
```

Projeyi IDE'nizde açın ve `Main` sınıfını çalıştırın.
</p>


![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)
<!-- USER MANAGEMENT -->
<h2 id="kullanici-yonetimi"> :busts_in_silhouette: Kullanıcı Yönetimi</h2>

<p align="justify">
Sistemde iki farklı kullanıcı tipi bulunmaktadır: Admin ve Acente Çalışanı. Admin kullanıcıları, sistem üzerinde kapsamlı kontrol ve yönetim yetkilerine sahiptir. Bu yetkiler arasında acente çalışanlarını listeleme, ekleme, silme, güncelleme ve kullanıcı rollerine göre filtreleme işlemleri yer alır. Acente çalışanları ise otel ve oda yönetimi, dönem yönetimi, fiyat yönetimi gibi işlevlere erişim sağlar ve müşteri rezervasyonları üzerinde işlemler gerçekleştirebilir.
</p>

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)
<!-- HOTEL MANAGEMENT -->
<h2 id="otel-yonetimi"> :hotel: Otel Yönetimi</h2>

<p align="justify">
Otel Yönetimi, Patika Turizm Acentesi'nin anlaşmalı olduğu otelleri yönetmek için tasarlanmıştır. Acente çalışanları bu modül aracılığıyla otel listeleme ve yeni otel ekleme işlemlerini gerçekleştirebilir. Her otel kaydında, otelin adı, adresi, e-posta adresi, telefon numarası, yıldız sayısı ve otelin sahip olduğu tesis özellikleri gibi detaylar yer alır. Ayrıca, otellere ait pansiyon tipleri ve dönem bilgileri de bu modül üzerinden yönetilir, böylece fiyatlandırma ve rezervasyon işlemleri için gerekli altyapı sağlanmış olur.
</p>

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)
<!-- ROOM MANAGEMENT -->
<h2 id="oda-yonetimi"> :door: Oda Yönetimi</h2>

<p align="justify">
Oda Yönetimi, acente çalışanlarının oteller bünyesindeki odaları yönetebilmelerini sağlar. Bu modül aracılığıyla oda listeleme, yeni oda ekleme ve odaları otel adı, şehir ya da istenilen tarihe göre arama imkanı sunar. Oda kaydı sırasında, odanın tipi (tek kişilik, çift kişilik, suite vb.), oda özellikleri (yatak sayısı, televizyon, minibar, oyun konsolu vb.), oda fiyatı ve oda stoğu gibi bilgiler girilir.

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)
<!-- RESERVATION OPERATIONS -->
<h2 id="rezervasyon-islemleri"> :clipboard: Rezervasyon İşlemleri</h2>

<p align="justify">
Rezervasyon İşlemleri, acente çalışanlarının müşterilere hızlı ve etkili bir şekilde hizmet sunmalarını sağlar. Rezervasyon işlemi sırasında, müşteri iletişim bilgileri ve misafir bilgileri girilir. Bu bilgilere göre toplam fiyat otomatik olarak hesaplanır. Acente çalışanları rezervasyonları listeleyebilir, güncelleyebilir ve silebilirler. Bir rezervasyon işlemi tamamlandığında, ilgili odanın stoğu 1 azaltılır.
</p>


![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

<!-- PROGRAM SCREENSHOTS -->
<h2 id="sistem-gorselleri"> :camera: Program İçi Görseller</h2>

<p align="justify"> 

### Login Ekranı
1)
![](docs/images/main.png)

### Admin Paneli 
1)
![](docs/images/1.png)
2)
![](docs/images/5.png)

### Otel Yönetimi
1)
![](docs/images/13.png)
2)
![](docs/images/10.png)

### Pansiyon Yönetimi
1)
![](docs/images/3.png)

### Sezon Yönetimi
1)
![](docs/images/4.png)

### Oda Yönetimi
1)
![](docs/images/6.png)
2)
![](docs/images/7.png)

### Rezervasyon İşlemleri
1)
![](docs/images/8.png)
2)
![](docs/images/11.png)

### Validasyonlar
1)
![](docs/images/12.png)
2)
![](docs/images/9.png)
3)
![](docs/images/2.png)

</p>


![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

<!-- CONTACT -->
<h2 id="iletisim"> :phone: İletişim ve Bilgiler</h2>

<p>
✤ <a href="https://linkedin.com/in/esra-sen">LinkedIn</a> <br>
✤ <a href="https://github.com/esrasen">GitHub</a> <br>
</p>