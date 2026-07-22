import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api';

@Component({
  selector: 'app-epreuves',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './epreuves.html',
  styleUrl: './epreuves.css'
})
export class Epreuves implements OnInit {
  epreuves: any[] = [];
  disciplines: any[] = [];
  athletes: any[] = [];
  loading = true;

  newEpreuve = {
    nom: '',
    dateEpreuve: '',
    heureEpreuve: '10:00',
    disciplineId: ''
  };

  selectedEpreuve: any = null;
  podiumForm = {
    epreuveId: null,
    athleteOrId: '',
    athleteArgentId: '',
    athleteBronzeId: ''
  };

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.chargerEpreuves();
    this.chargerDisciplines();
    this.chargerAthletes();
  }

  chargerEpreuves(): void {
    this.apiService.getEpreuves().subscribe({
      next: (data) => {
        console.log('Épreuves reçues du backend :', data);
        this.epreuves = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Erreur chargement épreuves:', err);
        this.loading = false;
      }
    });
  }

  chargerDisciplines(): void {
    this.apiService.getDisciplines().subscribe({
      next: (data) => (this.disciplines = data),
      error: (err) => console.error('Erreur chargement disciplines:', err)
    });
  }

  chargerAthletes(): void {
    this.apiService.getAthletes().subscribe({
      next: (data) => (this.athletes = data),
      error: (err) => console.error('Erreur chargement athlètes:', err)
    });
  }

  saveEpreuve(): void {
    if (!this.newEpreuve.nom || !this.newEpreuve.disciplineId) return;

    const payload: any = {
      nom: this.newEpreuve.nom,
      discipline: {
        id: Number(this.newEpreuve.disciplineId)
      }
    };

    if (this.newEpreuve.dateEpreuve) {
      const heure = this.newEpreuve.heureEpreuve || '00:00';
      payload.dateEpreuve = `${this.newEpreuve.dateEpreuve}T${heure}:00`;
    }

    console.log('Payload LocalDateTime envoyé :', payload);

    this.apiService.createEpreuve(payload).subscribe({
      next: () => {
        this.newEpreuve = { nom: '', dateEpreuve: '', heureEpreuve: '10:00', disciplineId: '' };
        this.chargerEpreuves();
      },
      error: (err) => {
        console.error('Erreur lors de la création :', err);
      }
    });
  }

  supprimerEpreuve(id: number): void {
    if (confirm('Voulez-vous vraiment supprimer cette épreuve ?')) {
      this.apiService.deleteEpreuve(id).subscribe({
        next: () => {
          console.log(`Épreuve #${id} supprimée`);
          this.chargerEpreuves();
        },
        error: (err) => console.error('Erreur lors de la suppression :', err)
      });
    }
  }

  isPastDate(dateVal: any): boolean {
    if (!dateVal) return false;
    const eventDate = new Date(dateVal);
    const now = new Date();
    return eventDate < now;
  }

  openPodiumModal(epreuve: any): void {
    this.selectedEpreuve = epreuve;
    this.podiumForm = {
      epreuveId: epreuve.id,
      athleteOrId: '',
      athleteArgentId: '',
      athleteBronzeId: ''
    };
  }

  enregistrerPodium(): void {
    const medailles = [
      { athleteId: this.podiumForm.athleteOrId, type: 'OR', rang: 1 },
      { athleteId: this.podiumForm.athleteArgentId, type: 'ARGENT', rang: 2 },
      { athleteId: this.podiumForm.athleteBronzeId, type: 'BRONZE', rang: 3 }
    ];

    medailles.forEach((m) => {
      if (m.athleteId) {
        const payload = {
          epreuve: { id: Number(this.podiumForm.epreuveId) },
          athlete: { id: Number(m.athleteId) },
          medaille: m.type,
          rang: m.rang
        };

        console.log('Payload résultat envoyé à Spring Boot :', payload);

        this.apiService.saveResultat(payload).subscribe({
          next: (res) => console.log('Résultat enregistré avec succès :', res),
          error: (err) => console.error('Erreur enregistrement résultat :', err)
        });
      }
    });
  }
}
