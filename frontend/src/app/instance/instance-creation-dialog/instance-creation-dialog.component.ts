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

  createInstance(name: string, url: string) {
    this.service.createInstance({
      "name": name,
      "url": url
    }).subscribe(params => {
      this.dialogRef.close(params);
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
    if (name && url) {
      this.createInstance(name.value, url.value);
    }
  }
}
