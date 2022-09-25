import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UrlHandlingStrategy } from '@angular/router';
import { Instance } from '../instance';
import { InstanceCreationDialogComponent } from '../instance-creation-dialog/instance-creation-dialog.component';
import { InstanceModificationDialogComponent } from '../instance-modification-dialog/instance-modification-dialog.component';
import { InstanceService } from '../instance.service';

@Component({
  selector: 'app-instance-view',
  templateUrl: './instance-view.component.html',
  styleUrls: ['./instance-view.component.css']
})
export class InstanceViewComponent implements OnInit {
  instances: Instance[] = [];
  editMode: boolean = false;
  hoverElement?: Instance | null = null;

  constructor(private service: InstanceService, private dialog: MatDialog) { }

  getInstances() {
    this.service.retrieveInstances().subscribe(params => {
      this.instances = params;
    });
  }

  openInstanceCreationDialog() {
    this.dialog.open(InstanceCreationDialogComponent)
      .afterClosed()
      .subscribe(() => this.ngOnInit());
  }

  openInstanceModificationDialog(id?: number, name?: string, url?: string, iconPath?: string) {
    this.dialog.open(InstanceModificationDialogComponent, {
      data: {
        "id": id,
        "name": name,
        "url": url,
        "iconPath": iconPath,
      }
    })
      .afterClosed()
      .subscribe(() => this.ngOnInit());
  }

  ngOnInit(): void {
    this.getInstances();
  }
}
