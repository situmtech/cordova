import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WYFPage } from './wyf.page';

describe('WYFPage', () => {
  let component: WYFPage;
  let fixture: ComponentFixture<WYFPage>;

  beforeEach(async () => {
    fixture = TestBed.createComponent(WYFPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
