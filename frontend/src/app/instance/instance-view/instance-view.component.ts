import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {Instance} from '../instance';
import {InstanceCreationDialogComponent} from '../instance-creation-dialog/instance-creation-dialog.component';
import {
  InstanceModificationDialogComponent
} from '../instance-modification-dialog/instance-modification-dialog.component';
import {InstanceService} from '../instance.service';

@Component({
  selector: 'app-instance-view',
  templateUrl: './instance-view.component.html',
  styleUrls: ['./instance-view.component.css']
})
export class InstanceViewComponent implements OnInit {
  categories: string[] = [];
  instances: Instance[] = [];

  constructor(private service: InstanceService, private dialog: MatDialog) {
  }

  getInstances() {
    this.service.retrieveInstances().subscribe(params => {
      this.instances = params;
      this.getCategories();
    });
  }

  getCategories() {
    this.categories = [];
    this.instances.forEach((instance: Instance) => {
      if (instance.category) {
        if (!this.categories.find(elem => elem == instance.category)) {
          this.categories.push(instance.category);
        }
      }
    });
  }

  getInstancesForCategory(category: string): Instance[] {
    let returnInstances: Instance[] = [];
    this.instances.forEach((instance) => {
      if (instance.category == category) {
        returnInstances.push(instance);
      }
    })
    return returnInstances;
  }

  openInstanceCreationDialog() {
    this.dialog.open(InstanceCreationDialogComponent)
      .afterClosed()
      .subscribe(() => this.ngOnInit());
  }

  openInstanceModificationDialog(id?: number, name?: string, url?: string, iconPath?: string, category?: string) {
    this.dialog.open(InstanceModificationDialogComponent, {
      data: {
        "id": id,
        "name": name,
        "url": url,
        "iconPath": iconPath,
        "category": category
      }
    })
      .afterClosed().subscribe(() => this.ngOnInit());
  }

  ngOnInit(): void {
    this.getInstances();
  }
}
