import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api';

@Component({
  selector: 'app-tableau-medailles',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './tableau-medailles.html',
  styleUrl: './tableau-medailles.css'
})
export class TableauMedailles implements OnInit {
  tableau: any[] = [];
  loading = true;

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.chargerTableau();
  }

  chargerTableau(): void {
    this.apiService.getTableauMedailles().subscribe({
      next: (data) => {
        this.tableau = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Erreur chargement médailles:', err);
        this.loading = false;
      }
    });
  }
}
