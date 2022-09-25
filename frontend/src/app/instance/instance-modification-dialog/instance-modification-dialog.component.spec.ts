import {ComponentFixture, TestBed} from '@angular/core/testing';

import {InstanceModificationDialogComponent} from './instance-modification-dialog.component';

describe('InstanceModificationDialogComponent', () => {
  let component: InstanceModificationDialogComponent;
  let fixture: ComponentFixture<InstanceModificationDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [InstanceModificationDialogComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(InstanceModificationDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
