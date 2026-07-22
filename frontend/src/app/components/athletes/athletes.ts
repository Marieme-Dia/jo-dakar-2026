import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api';

@Component({
  selector: 'app-athletes',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './athletes.html',
  styleUrl: './athletes.css'
})
export class Athletes implements OnInit {
  athletes: any[] = [];
  disciplines: any[] = [];
  loading = true;
  isEditing = false;

  newAthlete: any = {
    id: null,
    prenom: '',
    nom: '',
    pays: '',
    disciplineId: ''
  };

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.chargerAthletes();
    this.chargerDisciplines();
  }

  chargerAthletes(): void {
    this.apiService.getAthletes().subscribe({
      next: (data) => {
        console.log('Athlètes reçus du Backend :', data);
        this.athletes = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Erreur chargement athlètes:', err);
        this.loading = false;
      }
    });
  }

  chargerDisciplines(): void {
    this.apiService.getDisciplines().subscribe({
      next: (data) => this.disciplines = data,
      error: (err) => console.error(err)
    });
  }

  saveAthlete(): void {
    if (!this.newAthlete.prenom || !this.newAthlete.nom) return;

    // Payload "super-robuste" qui couvre tous les noms de propriétés possibles en Java
    const payload: any = {
      nom: this.newAthlete.nom,
      prenom: this.newAthlete.prenom,

      // On envoie tous les synonymes de Pays
      pays: this.newAthlete.pays,
      nationalite: this.newAthlete.pays,
      country: this.newAthlete.pays,

      // On envoie la discipline à la fois en ID direct et en objet relationnel Jackson/JPA
      disciplineId: this.newAthlete.disciplineId ? Number(this.newAthlete.disciplineId) : null
    };

    if (this.newAthlete.disciplineId) {
      payload.discipline = { id: Number(this.newAthlete.disciplineId) };
    }

    console.log('Payload Athlète envoyé :', payload);

    if (this.isEditing && this.newAthlete.id) {
      this.apiService.updateAthlete(this.newAthlete.id, payload).subscribe({
        next: () => {
          this.resetForm();
          this.chargerAthletes();
        },
        error: (err) => console.error('Erreur modification athlète :', err)
      });
    } else {
      this.apiService.createAthlete(payload).subscribe({
        next: () => {
          this.resetForm();
          this.chargerAthletes();
        },
        error: (err) => console.error('Erreur création athlète :', err)
      });
    }
  }

  editAthlete(athlete: any): void {
    this.isEditing = true;
    this.newAthlete = {
      id: athlete.id,
      prenom: athlete.prenom,
      nom: athlete.nom,
      pays: athlete.pays || athlete.nationalite || athlete.country || '',
      disciplineId: athlete.discipline?.id || athlete.disciplineId || ''
    };
  }

  deleteAthlete(id: number): void {
    if (confirm('Voulez-vous vraiment supprimer cet athlète ?')) {
      this.apiService.deleteAthlete(id).subscribe({
        next: () => this.chargerAthletes(),
        error: (err) => console.error(err)
      });
    }
  }

  resetForm(): void {
    this.isEditing = false;
    this.newAthlete = {
      id: null,
      prenom: '',
      nom: '',
      pays: '',
      disciplineId: ''
    };
  }
}
