import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstanceCreationDialogComponent } from './instance-creation-dialog.component';

describe('InstanceCreationDialogComponent', () => {
  let component: InstanceCreationDialogComponent;
  let fixture: ComponentFixture<InstanceCreationDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstanceCreationDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InstanceCreationDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
