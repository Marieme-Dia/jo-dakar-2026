import { Routes } from '@angular/router';
import { Athletes } from './components/athletes/athletes';
import { Disciplines } from './components/disciplines/disciplines';
import { Epreuves } from './components/epreuves/epreuves';
import { TableauMedailles } from './components/tableau-medailles/tableau-medailles';
import { DashboardComponent } from './components/dashboard/dashboard';

export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'tableau-medailles', component: TableauMedailles },
  { path: 'athletes', component: Athletes },
  { path: 'disciplines', component: Disciplines },
  { path: 'epreuves', component: Epreuves },
  { path: '**', redirectTo: 'dashboard' }
];
