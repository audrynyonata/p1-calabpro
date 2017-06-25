p1-calabpro  
Project 1 Ca Labpro

# README versi 26 Juni 2017             
Older version: 22 Mei 2017. 
Update: Menambah instruksi pemakaian dilengkapi screenshot program. 

NIM - Nama : 13515087 - Audry Nyonata

## Pengenalan Singkat.
1. JSON  
   JSON (JavaScript Object Notation) adalah sebuah format yang dibaca selama pembangunan struktur data. JSON bersifat minimal dan ringan, sehingga mudah dibaca dan ditulis oleh manusia, serta mudah untuk diparse dan digenerate oleh mesin. Utamanya JSON digunakan untuk memindahkan data antara server dan web application, sebagai pengganti XML.

   *Parse : menguraikan format file  
   *Generate : menghasilkan format file

   https://developers.squarespace.com/what-is-json/
   www.json.org/

2. REST, hampir selalu memanfaatkan protokol HTTP.   
   REST (Representational State Transfer) adalah sebuah gaya arsitektur yang digunakan untuk perancangan aplikasi jaringan (networked applications). REST menentukan berbagai batasan yang jika diaplikasikan pada sebuah layanan web dapat membantu menghasilkan ciri atau sifat tertentu yang kita ingingkan, dilihat dari segi performa, skalabilitas, dan modifiabilitas, sehingga layanan web dapat bekerja dengan optimal.

   6 batasan yang diatur oleh REST yaitu,
      1. Uniform Interface, menyederhanakan interface client dengan server, decoupling arsitektur agar setiap bagian dapat berkembang secara independen.  
      2. Stateless, status yang dibutuhkan untuk menangani request terkandung dalam request itu sendiri, misalnya sebagai bagian dari URI atau query.
      3. Cacheable, clients dapat menyimpan responses dalam cache, untuk meningkatkan performa dan kinerja sistem.
      4. Client-Server, client dan server terpisah oleh interface, clients tidak dipusingkan dengan data yang merupakan tanggung jawab server, sebaliknya server tidak dipusingkan dengan status user atau  user interface.
      5. Layered System, keterhubungan client-intermediary server-end server berlapis, layer ini dapat meningkatkan keamanan dan keseimbangan sistem.
      6. Code on Demand (optional), memampukan layanan untuk menambah (extend) atau mengkustomisasi fungsionalitas client melalui transfer logika.   

   *Skalabilitas: Kemampuan terukur sebuah proses komputasi untuk digunakan atau dibuat.  
   *Modifiabilitas: Kemampuan mengubah wujud atau sifat benda secara parsial/tidak menyeluruh.

   http://stackoverflow.com/questions/671118/what-exactly-is-restful-programming
   http://docs.oracle.com/javaee/6/tutorial/doc/gijqy.html
   http://restapitutorial.com/lessons/whatisrest.html

3. Github API  
   Sebuah API dibangun dan dirancang untuk memudahkan pengguna dalam menggunakan sebuah aplikasi tanpa perlu mempelajari seluruh bagian source codenya. Github adalah layanan penyimpanan file git yang mendukung social coding. Github API menawarkan REST API yang dapat dimanfaatkan untuk mengakses resource atau sumber daya yang dimiliki Github, dengan catatan resource tersebut telah sengaja diatur oleh Github untuk diekspos ke publik.

   https://developer.github.com/v3/.
   https://blogs.infosupport.com/accessing-githubs-rest-api-with-curl/

## Instruksi pemakaian program.
1. Jalankan program. Tombol search aktif jika keyword tidak kosong.  
   <img src="https://user-images.githubusercontent.com/23259980/27519581-3c05a262-5a21-11e7-9edc-aa5ec4e1ee33.png" width="400px" height="400px">  
2. Ketik keyword yang ingin dicari. Mode pencarian dapat dilakukan dengan mencari keyword di username, e-mail, atau nama lengkap pengguna. Isi filter dengan angka jika perlu, angka yang diijinkan yaitu 0 s.d. 99999999.  
   <img src="https://user-images.githubusercontent.com/23259980/27519582-3c08adb8-5a21-11e7-8c62-ba0a7f31ed3a.png" width="400px" height="400px">  
3. Klik tombol search.  
   <img src="https://user-images.githubusercontent.com/23259980/27519583-3c0d3f2c-5a21-11e7-8ae9-849dd27d5ffb.png" width="400px" height="400px">  
4. Klik salah satu username hasil yang muncul. Infomasi dan profil user tersebut akan ditampilkan. 
   <img src="https://user-images.githubusercontent.com/23259980/27519584-3c0dd57c-5a21-11e7-9e93-47b0f6e55d77.png" width="600px" height="420px">  
5. Jika pencarian tidak menemukan hasil yang sesuai, hasil yang muncul yaitu "Tidak ditemukan".
   <img src="https://user-images.githubusercontent.com/23259980/27519585-3c22908e-5a21-11e7-9ff5-822fab642c3a.png" width="400px" height="400px">  
6. Klik tombol close untuk menutup profil user. Klik tombol exit untuk keluar dari program. 

## Fitur-fitur lain.
1. Credits.  
   Credits berisi pernyataan serta ucapan terima kasih mengenai program ini.
2. UnknownHost Exception Error Message.  
   Jendela error message "Error: Lost Internet Connection" muncul ketika koneksi internet gagal dilakukan. Jika terjadi, sebaiknya mengecek hardware sambungan internet atau url dan server.
3. IOException Error Message.  
   Jendela error message "Error: Bad Authentication" muncul ketika koneksi internet sudah tersambung, namun server mengembalikan error code, pada program ini error 401, 403, dan 404 bisa diatasi dengan memperbaiki access token.
