import { canGoBack, canGoForward } from "../paginationUtils";

describe("canGoBack", () => {
  it("should return false if current page is null or undefined", () => {
    expect(canGoBack()).toBeFalsy();
    expect(canGoBack(null)).toBeFalsy();
  });

  it("should return true if current page is bigger than 0", () => {
    expect(canGoBack(1)).toBeTruthy();
    expect(canGoBack(100)).toBeTruthy();
    expect(canGoBack(9999)).toBeTruthy();
  });

  it("should return false if current page is 0", () => {
    expect(canGoBack(0)).toBeFalsy();
  });

  it("should return false if current page is negative", () => {
    expect(canGoBack(-1)).toBeFalsy();
    expect(canGoBack(-100)).toBeFalsy();
    expect(canGoBack(-9999)).toBeFalsy();
  });
});

describe("canGoForward", () => {
  it("should return false if any of the arguments is null or undefined", () => {
    expect(canGoForward(null, 10, 5)).toBeFalsy();
    expect(canGoForward(0, null, 5)).toBeFalsy();
    expect(canGoForward(0, 10, null)).toBeFalsy();
    expect(canGoForward(undefined, 10, 5)).toBeFalsy();
    expect(canGoForward(0, undefined, 5)).toBeFalsy();
    expect(canGoForward(0, 10, undefined)).toBeFalsy();
  });

  it("should return false if offset is negative or 0", () => {
    expect(canGoForward(0, 10, -1)).toBeFalsy();
    expect(canGoForward(0, 10, 0)).toBeFalsy();
  });

  it("should return true if trying to go to a valid page, from 0 to 1, when there's 2 pages", () => {
    expect(canGoForward(0, 10, 5)).toBeTruthy();
  });

  it("should return false if trying to go to invalid page, from 1 to 2, when there's 2 pages", () => {
    expect(canGoForward(1, 10, 5)).toBeFalsy();
  });
});