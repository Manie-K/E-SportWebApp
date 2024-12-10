import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganizationAddComponent } from './organization-add.component';

describe('CategoryAddComponent', () => {
  let component: OrganizationAddComponent;
  let fixture: ComponentFixture<OrganizationAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrganizationAddComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrganizationAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
