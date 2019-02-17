import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { SelectionModel } from '@angular/cdk/collections';
import { MatDialog, MatSnackBar } from '@angular/material';
import { EmployeeDialogComponent } from './employee-dialog/employee-dialog.component';
import { ToastMessage, ToastComponent } from './toast/toast.component';

export interface Employee {
  empId: string;
  fname: string;
  lname: string;
  dob: Date;
  department: string;
}


@Component({
  selector: 'k-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  columns = [
    {
      field: 'empId',
      label: 'Id'
    },
    {
      field: 'fname',
      label: 'First Name'
    },
    {
      field: 'lname',
      label: 'Last Name'
    },
    {
      field: 'dob',
      label: 'Date of Birth'
    },
    {
      field: 'department',
      label: 'Department'
    }
  ];

  displayedColumns = ['select'].concat(this.columns.map(c => c.field));
  selection = new SelectionModel<Employee>(true, []);
  dataSource: Employee[] = [];

  constructor(private http: HttpClient, private dialog: MatDialog, private snackBar: MatSnackBar) {

  }



  ngOnInit() {

    this.http.get<Employee[]>('/ui/v1/employee').subscribe(e => {
      this.dataSource = e;
    });

  }

  /** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.dataSource.forEach(row => this.selection.select(row));
  }

  trackById(index: number, emp: Employee) {
    return emp.empId;
  }

  remove() {
    if (this.selection.selected.length === this.dataSource.length) {
      this.selection.selected.forEach(e => {
        this.http.delete((`/ui/v1/employee/${e.empId}`)).subscribe();
      });
      this.selection.clear();
      this.dataSource = [];
    } else {
      this.selection.selected.forEach(e => {
        this.http.delete((`/ui/v1/employee/${e.empId}`)).subscribe(r => {
          this.dataSource = this.dataSource.filter(v => v.empId !== e.empId);
          this.selection.selected.splice(this.selection.selected.findIndex(v => e.empId === v.empId));
          if (this.selection.selected.length === 0) {
            this.selection.clear();
          }
        });
      });
    }

  }

  add(): void {
    const dialogRef = this.dialog.open(EmployeeDialogComponent, {
      width: '360px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      console.log(result);
    });
  }

  edit(): void {
    const dialogRef = this.dialog.open(EmployeeDialogComponent, {
      width: '360px',
      data: this.selection.selected[0]
    });


    dialogRef.afterClosed().subscribe(result => {
      result.empId = this.selection.selected[0].empId;
      const header = new HttpHeaders({
        'Content-Type': 'application/json'
      });
      this.http.put(`/ui/v1/employee/${result.empId}`, JSON.stringify(result), {
        headers: header
      }).subscribe(e => {
        this.selection.clear();
        this.dataSource = this.dataSource.map(x => x.empId === result.empId ? result : x);
        this.updateResult({
          type: 'info',
          title: 'Ops status',
          detail: 'update successfull!!',
          icon: 'info'
        });
      });
    });

  }


  updateResult(msg: ToastMessage) {
    this.snackBar.openFromComponent(ToastComponent, {
      duration: 1000,
      data: msg,
      panelClass: '' + msg.type
    });
  }

}
