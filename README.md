Credit Simulator

Aplikasi Credit Simulator untuk menghitung cicilan bulanan kredit kendaraan motor atau mobil, lengkap dengan aturan kredit, dan bunga bertingkat per tahun.
Aplikasi ini dibuat menggunakan pola arsitektur Factory, MVC, dengan bahasa pemrograman Java Springboot.

*Fitur Utama*
Simulasi Kredit Kendaraan
Aplikasi menerima input:
Jenis kendaraan: Motor | Mobil (case-insensitive)
Kondisi kendaraan: Baru | Bekas (case-insensitive)
Tahun kendaraan: 4 digit
Jumlah pinjaman (Total Loan): maksimal 1 miliar
Tenor pinjaman: 1–6 tahun
Jumlah DP (Down Payment)

Output:
Cicilan bulanan per tahun
Suku bunga tiap tahun (berdasarkan kenaikan bertingkat)

*Aturan Bunga & Perhitungan*
Bunga Dasar (Base Interest Rate)
Mobil: 8%
Motor: 9%

*Kenaikan Bunga*
+0.1% setiap 1 tahun
+0.5% setiap 2 tahun

*Rules / Validasi Input*
- Kendaraan NEW (Baru)
Tahun kendaraan tidak boleh < currentYear - 1
- Tenor maksimal 6 tahun
- DP Minimum
Mobil/Motor Baru → ≥ 35% dari jumlah pinjaman
Mobil/Motor Bekas → ≥ 25% dari jumlah pinjaman
- Jumlah pinjaman ≤ 1 miliar

*struktur aplikasi*
credit-simulator/
│
├── src/main
│   ├── controller/
│   │   └── CreditSimulatorController
│   ├── model/
│   │   └── CreditRequest
│   │   └── CreditResponse
│   ├── service/
│   │   └── impl
│   │   │   └── CreditMobilImpl
│   │   │   └── CreditMotorImpl
│   │   └── strategy
│   │   │   └── CreditSimulatorStrategy
│   │   │   └── CreditSimulatorStrategyFactory
│   │   └── CreditValidationService
│   └── view/
│       └── CommandMenu
│       └── SheetManager
│  ├── resouces/
│       └── file_inputs.txt
├── tests/
│   └── CreditsimulatorApplicationTest
└── target/
│   └── creditsimulator-0.0.1-SNAPSHOT.jar
│
└── README.md

Contoh Flow Input
> show
=== COMMAND LIST ===
calculate   : Hitung cicilan baru
load        : Load existing calculation dari file/API
show        : Tampilkan semua perintah
save sheet  : Simpan hasil ke sheet baru
switch sheet: Pindah ke sheet lain
exit        : Keluar aplikasi
====================
> calculate
Jenis Kendaraan (Motor/Mobil): mobil
Kondisi (Baru/Bekas): bekas
Tahun Kendaraan: 2011
Total Pinjaman: 100000000
Tenor (1-6 tahun): 3
DP: 25000000

Output
Tahun 1 : Rp.  2,250,000.00/bln, Suku Bunga: 8.0%
Tahun 2 : Rp.  2,432,250.00/bln, Suku Bunga: 8.1%
Tahun 3 : Rp.  2,641,423.50/bln, Suku Bunga: 8.6%
Sheet 'Sheet_1763645475374' tersimpan dan aktif.
> 
