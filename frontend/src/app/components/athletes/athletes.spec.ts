import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Athletes } from './athletes';

describe('Athletes', () => {
  let component: Athletes;
  let fixture: ComponentFixture<Athletes>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Athletes],
    }).compileComponents();

    fixture = TestBed.createComponent(Athletes);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
