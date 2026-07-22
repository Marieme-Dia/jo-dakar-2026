import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private baseUrl = 'http://localhost:8081/api';

  constructor(private http: HttpClient) { }

  // --- ATHLÈTES ---
  getAthletes(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/athletes`);
  }

  createAthlete(athlete: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/athletes`, athlete);
  }

  updateAthlete(id: number, athlete: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/athletes/${id}`, athlete);
  }

  patchAthlete(id: number, updates: any): Observable<any> {
    return this.http.patch<any>(`${this.baseUrl}/athletes/${id}`, updates);
  }

  deleteAthlete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/athletes/${id}`);
  }

  searchAthletes(params: any): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/athletes/recherche`, { params });
  }

  // --- DISCIPLINES ---
  getDisciplines(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/disciplines`);
  }

  createDiscipline(discipline: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/disciplines`, discipline);
  }

  deleteDiscipline(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/disciplines/${id}`);
  }

  // --- ÉPREUVES ---
  getEpreuves(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/epreuves`);
  }

  createEpreuve(epreuve: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/epreuves`, epreuve);
  }

  deleteEpreuve(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/epreuves/${id}`);
  }

  // --- RÉSULTATS & MÉDAILLES ---
  saveResultat(resultat: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/resultats`, resultat);
  }

  getTableauMedailles(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/resultats/tableau-medailles`);
  }

  getPodium(epreuveId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/resultats/podium/${epreuveId}`);
  }

  // --- TABLEAU DE BORD (DASHBOARD) ---
  getDashboardStats(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/dashboard`);
  }

}
