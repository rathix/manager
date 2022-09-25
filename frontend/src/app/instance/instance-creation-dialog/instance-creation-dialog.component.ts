import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {InstanceService} from '../instance.service';
import {MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-instance-creation-dialog',
  templateUrl: './instance-creation-dialog.component.html',
  styleUrls: ['./instance-creation-dialog.component.css']
})
export class InstanceCreationDialogComponent implements OnInit {
  form!: FormGroup;

  constructor(private service: InstanceService, private dialogRef: MatDialogRef<InstanceCreationDialogComponent>, private fb: FormBuilder) {
  }

  createStaticInstance(name: string, url: string, iconPath: string, category: string) {
    this.service.createInstance({
      "name": name,
      "url": url,
      "iconPath": iconPath,
      "category": category
    }).subscribe({
      next: Instance => this.dialogRef.close(),
      error: (e) => alert(e.error)
    });
  }

  createDynamicInstance(name: string, category?: string) {
    this.service.createInstance({
      "name": name,
      "url": name + ".rathix.com",
      "iconPath": "/assets/png/" + name + ".png",
      "category": category || "default"
    }).subscribe({
      next: Instance => this.dialogRef.close(),
      error: (e) => alert(e.error)
    });
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      name: ['', Validators.required],
      url: ['', Validators.required],
      iconPath: [''],
      category: ['']
    })
  }

  save() {
    let name = this.form.get("name")?.value.toLowerCase();
    let url = this.form.get("url")?.value.toLowerCase();
    let iconPath = this.form.get("iconPath")?.value.toLowerCase();
    let category = this.form.get("category")?.value.toLowerCase();
    if (name && url) {
      this.createStaticInstance(name, url, iconPath, category);
    } else if (name && !url) {
      this.createDynamicInstance(name, category);
    }
  }
}
