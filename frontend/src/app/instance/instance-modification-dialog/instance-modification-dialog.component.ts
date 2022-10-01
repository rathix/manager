import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {InstanceService} from '../instance.service';

@Component({
  selector: 'app-instance-modification-dialog',
  templateUrl: './instance-modification-dialog.component.html',
  styleUrls: ['./instance-modification-dialog.component.css']
})
export class InstanceModificationDialogComponent implements OnInit {
  form!: FormGroup;
  oldName: string;
  oldUrl: string;
  oldIconPath: string;
  oldCategory: string;

  constructor(private service: InstanceService, private dialogRef: MatDialogRef<InstanceModificationDialogComponent>, private fb: FormBuilder, @Inject(MAT_DIALOG_DATA) public data: any) {
    this.oldName = this.data.name;
    this.oldUrl = this.data.url;
    this.oldIconPath = this.data.iconPath;
    this.oldCategory = this.data.category;
  }

  updateInstance(id: string, name: string, url: string, iconPath: string, category: string) {
    this.service.updateInstance(id, {
      "name": name,
      "url": url,
      "iconPath": iconPath,
      "category": category
    }).subscribe({
      next: () => this.dialogRef.close(),
      error: (e) => alert(e.error)
    });
  }

  deleteInstance(id: string) {
    this.service.deleteInstance(id).subscribe(() => this.dialogRef.close());
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      name: [''],
      url: [''],
      iconPath: [''],
      category: ['']
    })
  }

  save() {
    let newName = this.form.get("name")?.value;
    let newUrl = this.form.get("url")?.value;
    let newIconPath = this.form.get("iconPath")?.value;
    let newCategory = this.form.get("category")?.value;
    this.updateInstance(this.data.id, newName || this.oldName, newUrl || this.oldUrl, newIconPath || this.oldIconPath, newCategory || this.oldCategory);
  }

  delete() {
    if (this.oldName && this.oldUrl) {
      this.deleteInstance(this.data.id);
    }
  }
}
