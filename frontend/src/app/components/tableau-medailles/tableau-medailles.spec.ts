import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableauMedailles } from './tableau-medailles';

describe('TableauMedailles', () => {
  let component: TableauMedailles;
  let fixture: ComponentFixture<TableauMedailles>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TableauMedailles],
    }).compileComponents();

    fixture = TestBed.createComponent(TableauMedailles);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
