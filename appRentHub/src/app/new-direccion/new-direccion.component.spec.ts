import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewDireccionComponent } from './new-direccion.component';

describe('NewDireccionComponent', () => {
  let component: NewDireccionComponent;
  let fixture: ComponentFixture<NewDireccionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewDireccionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewDireccionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
