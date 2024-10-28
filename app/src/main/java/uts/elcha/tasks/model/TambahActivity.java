package uts.elcha.tasks.model;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import uts.elcha.tasks.R;
import uts.elcha.tasks.helper.AgendaRepository;

public class TambahActivity extends AppCompatActivity {

    private EditText editTextNamaAgenda, editTextDeskripsi;
    private Spinner spinnerStatus;
    private Button buttonSimpan;
    private AgendaRepository agendaRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        editTextNamaAgenda = findViewById(R.id.et_agenda); // Pastikan ID ini sesuai dengan layout Anda
        editTextDeskripsi = findViewById(R.id.et_deskripsi);
        spinnerStatus = findViewById(R.id.spinner_status);
        buttonSimpan = findViewById(R.id.btn_simpan);

        // Inisialisasi repository
        agendaRepository = new AgendaRepository(this);

        // Buat array untuk status
        String[] statusOptions = {"Selesai", "Belum Selesai"};

        // Buat adapter untuk spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statusOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(adapter);

        buttonSimpan.setOnClickListener(v -> {
            String namaAgenda = editTextNamaAgenda.getText().toString().trim();
            String deskripsi = editTextDeskripsi.getText().toString().trim();
            String status = spinnerStatus.getSelectedItem().toString();

            if (namaAgenda.isEmpty() || deskripsi.isEmpty()) {
                Toast.makeText(TambahActivity.this, "Nama Agenda dan Deskripsi harus diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            // Simpan agenda baru ke database
            Agenda agenda = new Agenda(null, namaAgenda, deskripsi, status);
            agendaRepository.tambahAgenda(agenda); // Menggunakan objek agendaRepository

            Toast.makeText(TambahActivity.this, "Agenda berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            finish(); // Kembali ke activity sebelumnya
        });
    }
}

