import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SDKPage } from './sdk.page';

describe('SDKPage', () => {
  let component: SDKPage;
  let fixture: ComponentFixture<SDKPage>;

  beforeEach(async () => {
    fixture = TestBed.createComponent(SDKPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
