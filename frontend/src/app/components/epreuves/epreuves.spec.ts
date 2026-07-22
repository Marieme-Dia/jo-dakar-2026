import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Epreuves } from './epreuves';

describe('Epreuves', () => {
  let component: Epreuves;
  let fixture: ComponentFixture<Epreuves>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Epreuves],
    }).compileComponents();

    fixture = TestBed.createComponent(Epreuves);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
