import React from "react";
import PropTypes from "prop-types";
import "./style.scss";

export const TableHeader = ({
  labels
}) => (
  <div className="tr">
    {labels.map(label => (
      <div key={label} className="td-title">
        {label}
      </div>
    ))}
  </div>
);

export const TableRow = ({
  columns,
  item
}) => (
  <div className="tr">
    {columns.map(col => (
      <div key={col.value} className="td">
        {item[col.value] ? item[col.value] : ""}
      </div>
    ))}
  </div>
);

const Table = ({
  columns,
  items,
  itemIdProp
}) => {
  const labels = columns.map(col => col.label);

  return (
    <div className="table">
      <TableHeader labels={labels} />
      {
        items.map(item => (
          <TableRow
            key={item[itemIdProp]}
            item={item}
            columns={columns}
          />
        ))
      }
    </div>
  );
};

Table.propTypes = {
  /**
   * Columns to show in the table.
   * Label identifies the string that should appear as the column title
   * Value is the property name of the items that will populate the table
   */
  columns: PropTypes.arrayOf(
    PropTypes.shape({
      label: PropTypes.string,
      value: PropTypes.string
    })
  ),
  /**
   * Array of items to populate the table. Each item will be a row in the table.
   */
  items: PropTypes.arrayOf(
    PropTypes.object
  ),
  /**
   * Identifies an unique property inside each item
   */
  itemIdProp: PropTypes.oneOfType([
    PropTypes.string,
    PropTypes.number
  ])
};

Table.defaultProps = {
  columns: [],
  values: [],
  itemIdProp: "id"
};

export default Table;