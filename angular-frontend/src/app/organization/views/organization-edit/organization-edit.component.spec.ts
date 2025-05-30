import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganizationEditComponent } from './organization-edit.component';

describe('CategoryEditComponent', () => {
  let component: OrganizationEditComponent;
  let fixture: ComponentFixture<OrganizationEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrganizationEditComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrganizationEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
