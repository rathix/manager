import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { InstanceCreationDialogComponent } from '../instance-creation-dialog/instance-creation-dialog.component';
import { InstanceService } from '../instance.service';

@Component({
  selector: 'app-instance-modification-dialog',
  templateUrl: './instance-modification-dialog.component.html',
  styleUrls: ['./instance-modification-dialog.component.css']
})
export class InstanceModificationDialogComponent implements OnInit {
  form!: FormGroup;
  oldName: string;
  oldUrl: string;
  
  constructor(private service: InstanceService, private dialogRef: MatDialogRef<InstanceModificationDialogComponent>, private fb: FormBuilder, @Inject(MAT_DIALOG_DATA) public data: any) {
    this.oldName = this.data.name;
    this.oldUrl = this.data.url;
  }

  updateInstance(id: string, name: string, url: string) {
    this.service.updateInstance(id, {
      "name": name,
      "url": url
    }).subscribe(params => {
      this.dialogRef.close(params);
    });
  }

  deleteInstance(id: string) {
    this.service.deleteInstance(id).subscribe(() => this.dialogRef.close());
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      name: ['', Validators.required],
      url: ['', Validators.required]
    })
  }

  save() {
    let newName = this.form.get("name")?.value;
    let newUrl = this.form.get("url")?.value;
    if (newName && newUrl) {
      this.updateInstance(this.data.id, newName, newUrl);
    }
  }

  delete() {
    if (this.oldName && this.oldUrl) {
      this.deleteInstance(this.data.id);
    }
  }
}
