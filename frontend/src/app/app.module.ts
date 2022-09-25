import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';

import { MatCardModule } from '@angular/material/card';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { InstanceViewComponent } from './instance/instance-view/instance-view.component'; 
import { MatGridListModule } from '@angular/material/grid-list'; 
import {MatButtonModule} from '@angular/material/button'; 
import { InstanceService } from './instance/instance.service';
import { HttpClientModule } from '@angular/common/http';
import {MatIconModule} from '@angular/material/icon';
import { InstanceCreationDialogComponent } from './instance/instance-creation-dialog/instance-creation-dialog.component';
import { InstanceModificationDialogComponent } from './instance/instance-modification-dialog/instance-modification-dialog.component'; 
import {MatDialogModule} from '@angular/material/dialog'; 
import {MatFormFieldModule} from '@angular/material/form-field'; 
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatInputModule} from '@angular/material/input'; 
import { RouterModule } from '@angular/router';
import {MatCheckboxModule} from '@angular/material/checkbox'; 
import {MatSlideToggleModule} from '@angular/material/slide-toggle'; 
import {MatTooltipModule} from '@angular/material/tooltip'; 
import {DragDropModule} from '@angular/cdk/drag-drop'; 

@NgModule({
  declarations: [
    AppComponent,
    InstanceViewComponent,
    InstanceCreationDialogComponent,
    InstanceModificationDialogComponent
  ],
  imports: [
    BrowserModule,
    NoopAnimationsModule,
    MatCardModule,
    MatGridListModule,
    MatButtonModule,
    HttpClientModule,
    MatIconModule,
    MatDialogModule,
    MatFormFieldModule,
    FormsModule, 
    ReactiveFormsModule,
    MatInputModule,
    RouterModule.forRoot([], {onSameUrlNavigation: 'reload'}),
    MatCheckboxModule,
    MatSlideToggleModule,
    MatTooltipModule,
    DragDropModule
  ],
  exports: [RouterModule],
  providers: [
    InstanceService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
