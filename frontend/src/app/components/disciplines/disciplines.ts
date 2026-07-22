import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api';

@Component({
  selector: 'app-disciplines',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './disciplines.html',
  styleUrl: './disciplines.css'
})
export class Disciplines implements OnInit {
  disciplines: any[] = [];
  loading = true;
  newDiscipline = { nom: '', description: '' };

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.chargerDisciplines();
  }

  chargerDisciplines(): void {
    this.apiService.getDisciplines().subscribe({
      next: (data) => {
        this.disciplines = data;
        this.loading = false;
      },
      error: (err) => console.error(err)
    });
  }

  saveDiscipline(): void {
    if (!this.newDiscipline.nom) return;
    this.apiService.createDiscipline(this.newDiscipline).subscribe(() => {
      this.newDiscipline = { nom: '', description: '' };
      this.chargerDisciplines();
    });
  }

  deleteDiscipline(id: number): void {
    if (confirm('Supprimer cette discipline ?')) {
      this.apiService.deleteDiscipline(id).subscribe(() => this.chargerDisciplines());
    }
  }
}
