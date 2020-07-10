import React from 'react';
import { shallow } from 'enzyme';
import Table, { TableHeader, TableRow } from '../Table';

const getTableWrapper = ({
  columns,
  items
}) => (
  shallow(
    <Table
      columns={columns}
      items={items}
    />
  )
);

const getTableHeaderWrapper = ({
  labels
}) => (
  shallow(
    <TableHeader
      labels={labels}
    />
  )
);

const getTableRowWrapper = ({
  item,
  columns
}) => (
  shallow(
    <TableRow
      item={item}
      columns={columns}
    />
  )
);

const mockColumns = [
  {
    label: "label1",
    value: "val1"
  },
  {
    label: "label2",
    value: "val2"
  }
];

const mockItems = [
  {
    val1: "item1_val1",
    val2: "item1_val2",
    id: "item1"
  },
  {
    val1: "item2_val1",
    val2: "item2_val2",
    id: "item2"
  },
  {
    val1: "item3_val1",
    val2: "item2_val2",
    id: "item3"
  }
];

describe("Table", () => {
  it("should render 1 TableHeader and 1 TableRow per item in items", () => {
    const wrapper = getTableWrapper({
      columns: mockColumns,
      items: mockItems
    });
    expect(wrapper.find(TableHeader).length).toEqual(1);
    expect(wrapper.find(TableHeader).at(0).props().labels).toEqual(["label1", "label2"]);
    expect(wrapper.find(TableRow).length).toEqual(3);
    const firstRowProps = wrapper.find(TableRow).at(0).props();
    expect(firstRowProps.item).toEqual(mockItems[0]);
    expect(firstRowProps.columns).toEqual(mockColumns);
  });

  it("should render 1 TableHeader and no TableRows if no items", () => {
    const wrapper = getTableWrapper({
      columns: mockColumns,
      items: []
    });
    expect(wrapper.find(TableHeader).length).toEqual(1);
    expect(wrapper.find(TableRow).length).toEqual(0);
  });
});

describe("TableHeader", () => {
  const mockLabels = ["label1", "label2", "label3"];
  const getContainer = wrapper => wrapper.find(".tr").at(0);
  const getCells = wrapper => wrapper.find(".td-title");

  it("should render a tr div and 1 td-title div per label in labels", () => {
    const wrapper = getTableHeaderWrapper({
      labels: mockLabels
    });
    expect(getContainer(wrapper).exists()).toBeTruthy();
    expect(getCells(wrapper).length).toEqual(3);
    expect(getCells(wrapper).at(0).props().children).toEqual("label1");
    expect(getCells(wrapper).at(1).props().children).toEqual("label2");
    expect(getCells(wrapper).at(2).props().children).toEqual("label3");
  });

  it("should render a tr div and no td-titles div when no labels", () => {
    const wrapper = getTableHeaderWrapper({
      labels: []
    });
    expect(getContainer(wrapper).exists()).toBeTruthy();
    expect(getCells(wrapper).length).toEqual(0);
  });
});

describe("TableRow", () => {
  const getContainer = wrapper => wrapper.find(".tr").at(0);
  const getCells = wrapper => wrapper.find(".td");

  it("should render a tr div and 1 td div per column in columns", () => {
    const wrapper = getTableRowWrapper({
      columns: mockColumns,
      item: mockItems[0]
    });
    expect(getContainer(wrapper).exists()).toBeTruthy();
    expect(getCells(wrapper).length).toEqual(2); // 2 columns
    expect(getCells(wrapper).at(0).props().children).toEqual(mockItems[0]["val1"]);
    expect(getCells(wrapper).at(1).props().children).toEqual(mockItems[0]["val2"]);
  });

  it("should render a tr div and no td div when no columns", () => {
    const wrapper = getTableRowWrapper({
      columns: [],
      item: mockItems[0]
    });
    expect(getContainer(wrapper).exists()).toBeTruthy();
    expect(getCells(wrapper).length).toEqual(0);
  });

  it("should render an empty string in the cell content, if item does not have the property", () => {
    const wrapper = getTableRowWrapper({
      columns: [...mockColumns, {
        label: "label3",
        value: "val3"
      }],
      item: mockItems[0]
    });
    expect(getCells(wrapper).length).toEqual(3);
    expect(getCells(wrapper).at(2).props().children).toEqual("");
  });
});
