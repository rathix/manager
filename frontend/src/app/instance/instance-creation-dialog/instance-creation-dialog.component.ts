import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { InstanceService } from '../instance.service';
import { MatDialogRef } from '@angular/material/dialog';
import { Instance } from '../instance';

@Component({
  selector: 'app-instance-creation-dialog',
  templateUrl: './instance-creation-dialog.component.html',
  styleUrls: ['./instance-creation-dialog.component.css']
})
export class InstanceCreationDialogComponent implements OnInit {
  form!: FormGroup;
  
  constructor(private service: InstanceService, private dialogRef: MatDialogRef<InstanceCreationDialogComponent>, private fb: FormBuilder) { }

  createStaticInstance(name: string, url: string) {
    this.service.createInstance({
      "name": name,
      "url": url
    }).subscribe({
      next: Instance => this.dialogRef.close(),
      error: (e) => alert(e.error)
    });
  }

  createDynamicInstance(name: string) {
    this.service.createInstance({
      "name": name,
      "url": name + ".rathix.com"
    }).subscribe({
      next: Instance => this.dialogRef.close(),
      error: (e) => alert(e.error)
    });
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      name: ['', Validators.required],
      url: ['', Validators.required]
    })
  }

  save() {
    let name = this.form.get("name");
    let url = this.form.get("url");
    if (name?.value && url?.value) {
      this.createStaticInstance(name.value, url.value);
    } else if (name?.value && !url?.value) {
      this.createDynamicInstance(name.value);
    }
  }
}
